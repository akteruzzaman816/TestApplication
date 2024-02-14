package com.akter.testlibrary

import android.content.Context
import android.content.pm.PackageManager
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.widget.AppCompatImageView
import com.akter.testlibrary.model.AdModelResponse
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreviewAds(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(context,attrs){

    private val TAG = "#X_"
    private var adType:Int = 0

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.PreviewAds)
        adType = typeArray.getInt(R.styleable.PreviewAds_ad_type,1)
        makeApiCallForAdds()
        getApplicationMetadata()
    }
    private fun makeApiCallForAdds() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = HttpClient.getInstance().getData()
            if (response.isSuccessful){
                val data = response.body()
                setUpBanner(data)
            }
        }
    }

    private fun setUpBanner(data: AdModelResponse?) {
        CoroutineScope(Dispatchers.Main).launch{
            this@PreviewAds.let {
                Glide.with(context).load(data?.data?.get(adType)?.imageMobile).into(it)
                adjustViewBounds = true
            }
        }
    }

    private fun getApplicationMetadata() {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, PackageManager.GET_META_DATA)
            // Accessing metadata values
            val metaData = packageInfo.applicationInfo.metaData
            val metadataValue = metaData.getString(TestLibraryConstants.ACCOUNT_KEY_NAME)
            Log.d(TAG, "getApplicationMetadata: $metadataValue")
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }

}