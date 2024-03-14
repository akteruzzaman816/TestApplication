package com.akter.testlibrary

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.akter.testlibrary.dialog.AdviewDialog
import com.akter.testlibrary.model.BrowserInfo
import com.akter.testlibrary.model.Cookies
import com.akter.testlibrary.utils.AdfinixAdType
import com.akter.testlibrary.utils.SharedPref
import com.akter.testlibrary.utils.TestLibraryConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Adfinix {
    internal const val TAG = "#X_"
    internal var siteID: Int? = null
    internal var adType: AdfinixAdType? = null
    internal var cookieList: MutableList<Cookies>? = null
    private var screenSize = ""
    private var networkType = "4g"
    internal var deviceInfo: BrowserInfo? = null

    fun initialize(context: Context, siteID: Int, adType: AdfinixAdType) {
        this.siteID = siteID
        this.adType = adType
        SharedPref.init(context)
        getScreenInfo(context)
        getDeviceInfo()
        cookieList = Gson().fromJson(
            SharedPref.read(TestLibraryConstants.CACHE_DATA, ""),
            object : TypeToken<List<Cookies>>() {}.type
        )
        Log.d(TAG, "initialize: $cookieList")
    }


    @SuppressLint("SetJavaScriptEnabled")
    // show banner ads
    fun showAds(adfinixAds: AdfinixAds) {
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
    fun showFullScreenAds(context: Context, slotID: Int) {
        if (siteID != null) AdviewDialog(context, slotID).show()
        else throw Exception("showFullScreenAds: please do Adfinix initial setup")
    }

    fun saveCookiesData(cookies: Cookies?) {
        var cookiesFound = false
        cookies?.let { cookieData ->
            cookieList?.let {
                for (item in it) {
                    if (cookieData.adGroupId == item.adGroupId) {
                        item.adServed = item.adServed?.plus(1)
                        item.lastAdServed = cookieData.lastAdServed
                        cookiesFound = true
                    }
                }

                // if not found ad new cookies
                if (!cookiesFound) {
                    cookieData.lastAdServed = cookieData.expire
                    cookieData.adServed = 0
                    cookieList?.add(cookieData)
                }

                // save all the data to cache
                SharedPref.write(TestLibraryConstants.CACHE_DATA, Gson().toJson(cookieList))

            }
        }

    }

    private fun getScreenInfo(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.density
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels
        displayMetrics.densityDpi

        screenSize = "$widthPixels x $heightPixels"
    }

    @SuppressLint("MissingPermission")
    fun getInternetConnectionType(context: Context) {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val type = telephonyManager.networkType

        networkType = when (type) {
            TelephonyManager.NETWORK_TYPE_UNKNOWN -> "Unknown"
            TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> "2G"
            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> "3G"
            TelephonyManager.NETWORK_TYPE_LTE -> "4G"
            else -> "Unknown"
        }
    }

    private fun getDeviceInfo() {
        deviceInfo =  BrowserInfo(
            screen = screenSize,
            mobile = "yes",
            os = "android",
            deviceModel = Build.MODEL,
            deviceType = Build.TYPE,
            deviceVendor = Build.BRAND,
            osVersion = Build.VERSION.RELEASE,
            cookies = true,
            connectionStatus = networkType
        )
    }

    internal fun getCookiesData(): Map<String, MutableMap<String, Map<String, Any?>>> {

        val adGroups = mutableMapOf<String, Map<String, Any?>>()

        cookieList?.let {
            for (item in it) {
                adGroups[item.adGroupId.toString()] = mapOf(
                    "adgroup_id" to item.adGroupId,
                    "last_ad_served" to item.lastAdServed,
                    "ad_served" to item.adServed,
                    "expire" to item.expire
                )
            }
        }
        return mapOf("adgroups" to adGroups)

    }
}