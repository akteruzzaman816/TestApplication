package com.akter.testlibrary

import android.annotation.SuppressLint
import android.content.Context
import com.akter.testlibrary.dialog.AdviewDialog

object Adfinix {
    const val TAG = "#X_"
    @SuppressLint("SetJavaScriptEnabled")
    fun initialize(adfinixAds: AdfinixAds) {
        adfinixAds.apply {
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            settings.apply {
                loadWithOverviewMode = true
                useWideViewPort = true
                javaScriptEnabled = true
            }
        }
    }

    // show full screen ad
    fun showFullScreenAds(context: Context, slotID:Int){
        AdviewDialog(context).show()
    }

    // dummy data
    val cookies = "{\n" +
            "\t\t\"adgroups\": {\n" +
            "\t\t\t\"193\": {\n" +
            "\t\t\t\t\"adgroup_id\": 193,\n" +
            "\t\t\t\t\"last_ad_served\": 1705226851121,\n" +
            "\t\t\t\t\"ad_served\": 2,\n" +
            "\t\t\t\t\"expire\": 1705226850722204400\n" +
            "\t\t\t},\n" +
            "\t\t\t\"211\": {\n" +
            "\t\t\t\t\"adgroup_id\": 211,\n" +
            "\t\t\t\t\"last_ad_served\": 1705227567058,\n" +
            "\t\t\t\t\"ad_served\": 15,\n" +
            "\t\t\t\t\"expire\": 1705227566944194600\n" +
            "\t\t\t}\n" +
            "\t\t},\n" +
            "\t\t\"format\": \"impression\",\n" +
            "\t\t\"unique_user_key\": null\n" +
            "\t}"

    val browserInfo = "{\n" +
            "\t\t\"screen\": \"1536 x 864\",\n" +
            "\t\t\"browser\": \"Chrome\",\n" +
            "\t\t\"browserVersion\": 120,\n" +
            "\t\t\"mobile\": \"no\",\n" +
            "\t\t\"os\": \"Linux\",\n" +
            "\t\t\"device_model\": \"Unknown\",\n" +
            "\t\t\"device_type\": \"Unknown\",\n" +
            "\t\t\"device_vendor\": \"Unknown\",\n" +
            "\t\t\"osVersion\": \"Unknown\",\n" +
            "\t\t\"cookies\": true,\n" +
            "\t\t\"useragent\": \"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36\",\n" +
            "\t\t\"connection_status\": \"4g\"\n" +
            "\t}"
}