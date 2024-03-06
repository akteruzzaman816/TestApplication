package com.akter.testlibrary.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TestLibraryConstants {
    const val ACCOUNT_KEY_NAME = "com.adfinix.site_id"
    const val CACHE_DATA = "cache_data"

    fun <T> Gson.fromJsonList(jsonString: String): MutableList<T> =
        this.fromJson(jsonString, object : TypeToken<ArrayList<T>>() {}.type)
}