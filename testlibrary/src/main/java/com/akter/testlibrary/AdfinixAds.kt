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


class AdfinixAds(context: Context, attrs: AttributeSet? = null) :WebView(context,attrs){

    init {
        initialize(attrs)
    }

    private var adType:Int = 0
    private var initialUrl = ""

    @SuppressLint("SetJavaScriptEnabled", "Recycle", "CustomViewStyleable")
    fun initialize(attrs: AttributeSet?) {
        /** ad types **/
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AdfinixAds)
        adType = typeArray.getInt(R.styleable.AdfinixAds_adSlotId,1)

        /** handle events **/
        handleViewEvents()
        loadCustomAd()
    }


    private fun loadCustomAd() {
        when(adType){
            0 -> loadUrl(Adfinix.adUrls[0])
            1 -> loadUrl(Adfinix.adUrls[1])
            2 -> loadUrl(Adfinix.adUrls[2])
            3 -> loadUrl(Adfinix.adUrls[3])
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
                loadUrl(initialUrl)
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


}