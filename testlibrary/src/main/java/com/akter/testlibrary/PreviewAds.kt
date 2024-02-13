package com.akter.testlibrary

import android.content.Context
import android.content.pm.PackageManager
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView


class PreviewAds(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(context,attrs){
    private val TAG = "#X_"

    init {
        setImageResource(R.drawable.img)
        getApplicationMetadata()
    }

    private fun getApplicationMetadata() {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, PackageManager.GET_META_DATA)

            // Accessing metadata values
            val metaData = packageInfo.applicationInfo.metaData
            val metadataValue = metaData.getString("com.akter.testlibrary")
            Log.d(TAG, "getApplicationMetadata: $metadataValue")

            // Do something with metadataValue
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
    }
}