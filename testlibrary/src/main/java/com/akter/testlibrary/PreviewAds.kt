package com.akter.testlibrary

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class PreviewAds(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(context,attrs){

    init {
        setImageResource(R.drawable.img)
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
    }
}