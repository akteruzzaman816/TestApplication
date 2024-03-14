package com.akter.testlibrary.model

import androidx.annotation.Keep
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable


@Keep
data class ModelAdRequest(
    @SerializedName("browserInfo")
    val browserInfo: BrowserInfo?,
    @SerializedName("cookies")
    val cookies: AdRequestCookies?,
    @SerializedName("slotInfo")
    val slotInfo: SlotInfo?
) : Serializable

@Keep
data class BrowserInfo(
    @SerializedName("browser")
    val browser: String ? =null,
    @SerializedName("browserVersion")
    val browserVersion: Int? = null,
    @SerializedName("connection_status")
    val connectionStatus: String? = null,
    @SerializedName("cookies")
    val cookies: Boolean? = null,
    @SerializedName("device_model")
    val deviceModel: String? = null,
    @SerializedName("device_type")
    val deviceType: String? = null,
    @SerializedName("device_vendor")
    val deviceVendor: String? = null,
    @SerializedName("mobile")
    val mobile: String? = null,
    @SerializedName("os")
    val os: String? = null,
    @SerializedName("osVersion")
    val osVersion: String? = null,
    @SerializedName("screen")
    val screen: String? = null,
    @SerializedName("useragent")
    val useragent: String? = null
) : Serializable

@Keep
data class AdRequestCookies(
    @SerializedName("adgroups")
    val adgroups: JSONObject,
    @SerializedName("format")
    val format: String?,
    @SerializedName("unique_user_key")
    val uniqueUserKey: Any?
) : Serializable

@Keep
data class SlotInfo(
    @SerializedName("context")
    val context: String?,
    @SerializedName("is_above")
    val isAbove: Boolean?,
    @SerializedName("site_id")
    val siteId: Int?,
    @SerializedName("slot_id")
    val slotId: Int?,
    @SerializedName("url")
    val url: String?
) : Serializable



@Keep
data class CookiesData(
    @SerializedName("ad_served")
    val adServed: Int?,
    @SerializedName("adgroup_id")
    val adgroupId: Int?,
    @SerializedName("expire")
    val expire: Long?,
    @SerializedName("last_ad_served")
    val lastAdServed: Long?
) : Serializable