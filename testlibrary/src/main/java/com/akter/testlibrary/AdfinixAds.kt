package com.akter.testlibrary

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.akter.testlibrary.Adfinix.TAG


class AdfinixAds(context: Context, attrs: AttributeSet? = null) : LinearLayout(context) {
    private var adType:Int = 0
    init {
        initialize(attrs)
    }

    @SuppressLint("SetJavaScriptEnabled", "Recycle", "CustomViewStyleable")
    fun initialize(attrs: AttributeSet?) {
        // ad type
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AdfinixAds)
        adType = typeArray.getInt(R.styleable.AdfinixAds_adSlotId,0)

        // inflate ad views
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_preview, this)

        // ad views setup
        val adView = findViewById<WebView>(R.id.ad_view)
        adView.apply {
            isVisible = false
            handleViewEvents(adView)
            settings.javaScriptEnabled = true
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true

            loadCustomAd(adView)
        }
    }

    private fun loadCustomAd(adView: WebView?) {
        when(adType){
            0 -> adView?.loadUrl(Adfinix.adUrls[0])
            1 -> adView?.loadUrl(Adfinix.adUrls[1])
            2 -> adView?.loadUrl(Adfinix.adUrls[2])
            3 -> adView?.loadUrl(Adfinix.adUrls[3])
        }
    }

    private fun handleViewEvents(adfinixAds: WebView) {
        adfinixAds.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                adfinixAds.isVisible = false
                Log.d(TAG, "onPageStarted: ")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                adfinixAds.isVisible = true
                Log.d(TAG, "onPageFinished: ")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                adfinixAds.isVisible = false
                Log.d(TAG, "onReceivedError:${error?.description}")
                Log.d(TAG, "onReceivedError:${error}")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.d(TAG, "shouldOverrideUrlLoading: ${view?.url}")
                if (view?.url != null && view.url?.startsWith("http") == true) {
                    view.context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(view.url)))
                    return true
                }
                return false
            }
        }
    }



}