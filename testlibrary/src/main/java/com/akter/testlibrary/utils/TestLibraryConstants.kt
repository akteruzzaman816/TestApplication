package com.akter.testlibrary.utils

import android.annotation.SuppressLint
import android.util.Patterns
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TestLibraryConstants {
    const val ACCOUNT_KEY_NAME = "com.adfinix.site_id"
    const val CACHE_DATA = "cache_data"
    const val LIVE_SERVER = "https://bangladesh-staging.adfinix.com/"
    const val TEST_SERVER = "https://bangladesh-staging.adfinix.com/"

    fun <T> Gson.fromJsonList(jsonString: String): MutableList<T> =
        this.fromJson(jsonString, object : TypeToken<ArrayList<T>>() {}.type)

    fun validateUrl(url:String):Boolean{
        return Patterns.WEB_URL.matcher(url).matches()
    }

    @SuppressLint("SuspiciousIndentation")
    fun injectHtml(data:String):String{
        val html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                removeBackSlash(data)
                "</body>\n" +
                "</html>"

        return html
    }


    private fun removeBackSlash(data:String):String{

        return data.replace("\\","")
    }


}