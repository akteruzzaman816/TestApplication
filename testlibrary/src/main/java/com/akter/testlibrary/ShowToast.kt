package com.akter.testlibrary

import android.content.Context
import android.widget.Toast

class ShowToast {

    companion object{
        fun showToast(text: String?,mCtx:Context) {
            if (!text.isNullOrBlank()) {
                Toast.makeText(mCtx, text, Toast.LENGTH_SHORT).show()
            }
        }
    }


}