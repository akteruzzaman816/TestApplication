package com.akter.testlibrary

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import com.akter.testlibrary.Adfinix.TAG
import com.akter.testlibrary.model.AdCookies
import com.akter.testlibrary.model.BrowserInfo
import com.akter.testlibrary.model.Cookies
import com.akter.testlibrary.model.ModelAdRequest
import com.akter.testlibrary.model.ModelAdResponse
import com.akter.testlibrary.model.SlotInfo
import com.akter.testlibrary.utils.SharedPref
import com.akter.testlibrary.utils.TestLibraryConstants
import com.akter.testlibrary.utils.TestLibraryConstants.fromJsonList
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AdfinixAds(context: Context, attrs: AttributeSet? = null) :WebView(context,attrs){

    init {
        initialize(attrs)
    }

    private var slotID:Int = 0
    private var siteID:Int = 0
    private var initialUrl = ""

    @SuppressLint("SetJavaScriptEnabled", "Recycle", "CustomViewStyleable")
    fun initialize(attrs: AttributeSet?) {

        /** ad types **/
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AdfinixAds)
        slotID = typeArray.getInt(R.styleable.AdfinixAds_adSlotId,0)
        /** side id **/
        siteID = Adfinix.siteID ?: 0

        if (slotID != 0 && siteID != 0){
            //request for new ads
            makeApiCallForAdds()
        }


        /** handle events **/
        handleViewEvents()

        /** init share_pref **/
        SharedPref.init(context)
    }

    fun setupSlotID(id:Int) {
        slotID = id
        if (slotID != 0 && siteID != 0){
            //request for new ads
            makeApiCallForAdds()
        }
    }



    private fun handleViewEvents() {
        webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                if (initialUrl != url && initialUrl.isNotEmpty()){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                    loadUrl(initialUrl)
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                isVisible = true
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.d(TAG, "onReceivedError:${error?.description}")
            }


            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                initialUrl = view?.url ?:""
                Log.d(TAG, "shouldOverrideUrlLoading: $initialUrl")
                return super.shouldOverrideUrlLoading(view, request)
            }

        }
    }

    private fun makeApiCallForAdds() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = HttpClient.getInstance().getAdData(makeRequest())
            if (response.isSuccessful){
                loadAds(response.body())
            }
            Log.d(TAG, "response_data: ${Gson().toJson(response.body())}")
        }
    }

    private fun loadAds(body: ModelAdResponse?) {
        body?.advertizement?.adString?.let {
            // show ads
            CoroutineScope(Dispatchers.Main).launch {
                if (TestLibraryConstants.validateUrl(it)) loadUrl(it)
                else if (it.isNotEmpty()){
                   val injectUrl = TestLibraryConstants.injectHtml(it)
                    loadDataWithBaseURL(null, injectUrl, "text/html", "UTF-8", null)
                }
            }
        }

        // save cookies info
        saveCookies(body?.cookies)
    }

    private fun saveCookies(cookies: AdCookies?) {
        val cookieData = checkCookiesData(cookies)
        SharedPref.write(TestLibraryConstants.CACHE_DATA,Gson().toJson(cookieData))
        Log.d(TAG, "saveCookies: ${Gson().toJson(cookieData)}")
    }

    private fun checkCookiesData(cookies: AdCookies?): MutableList<AdCookies> {
        val data = SharedPref.read(TestLibraryConstants.CACHE_DATA,"")
        var cookiesList:MutableList<AdCookies> = mutableListOf()

        Log.d(TAG, "checkCookiesData: $data")

        if (data?.isNotEmpty() == true) cookiesList = Gson().fromJsonList(data)
        Log.d(TAG, "checkCookiesData__: ${Gson().toJson(cookiesList)}")
        for (item in cookiesList)
//        for (item in cookiesList){
//            Log.d(TAG, "check-- ${item.adGroupId}")
//            Log.d(TAG, "checkCookiesData: ${item.adGroupId} => ${cookies?.adGroupId}")
//            if (item.adGroupId == cookies?.adGroupId){
//                item.adServed = item.adServed!! + 1
//                item.lastAdServed = System.currentTimeMillis()
//                return cookiesList
//            }
//        }

        // add cookies to list
        cookies?.let {
            it.adServed = 1
            it.lastAdServed = System.currentTimeMillis()
            cookiesList.add(it)
        }
        return cookiesList
    }

    private fun sessionCheck(timestamp:Long):Boolean{
        return true
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        if (isInEditMode.not()) {
            super.onDraw(canvas)
            return
        }
        canvas.drawColor(Color.WHITE)
        val bitmap = ResourcesCompat.getDrawable(resources, R.drawable.logo, null)!!.toBitmap()
        val size = minOf(width, height) / 2
        val rect = Rect((width - size) / 2, (height - size) / 2, (width + size) / 2, (height + size) / 2)
        canvas.drawBitmap(bitmap, null, rect, null)
    }

    private fun makeRequest() : ModelAdRequest{
        val browserInfo = Gson().fromJson(Adfinix.browserInfo, BrowserInfo::class.java)
        val cookies = Gson().fromJson(Adfinix.cookies, Cookies::class.java)
        val req = ModelAdRequest(browserInfo,cookies, SlotInfo("",false,siteID,slotID,"adfinix.xyz"))
        Log.d(TAG, "ad_request_body: ${Gson().toJson(req)}")
        return req
    }


}