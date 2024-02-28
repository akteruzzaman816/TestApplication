package com.akter.testlibrary.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Keep
data class ModelAdRequest(
    @SerializedName("browserInfo")
    val browserInfo: BrowserInfo?,
    @SerializedName("cookies")
    val cookies: Cookies?,
    @SerializedName("slotInfo")
    val slotInfo: SlotInfo?
) : Serializable

@Keep
data class BrowserInfo(
    @SerializedName("browser")
    val browser: String?,
    @SerializedName("browserVersion")
    val browserVersion: Int?,
    @SerializedName("connection_status")
    val connectionStatus: String?,
    @SerializedName("cookies")
    val cookies: Boolean?,
    @SerializedName("device_model")
    val deviceModel: String?,
    @SerializedName("device_type")
    val deviceType: String?,
    @SerializedName("device_vendor")
    val deviceVendor: String?,
    @SerializedName("mobile")
    val mobile: String?,
    @SerializedName("os")
    val os: String?,
    @SerializedName("osVersion")
    val osVersion: String?,
    @SerializedName("screen")
    val screen: String?,
    @SerializedName("useragent")
    val useragent: String?
) : Serializable

@Keep
data class Cookies(
    @SerializedName("adgroups")
    val adgroups: Adgroups?,
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
data class Adgroups(
    @SerializedName("193")
    val x193: X193?,
    @SerializedName("211")
    val x211: X193?
) : Serializable

@Keep
data class X193(
    @SerializedName("ad_served")
    val adServed: Int?,
    @SerializedName("adgroup_id")
    val adgroupId: Int?,
    @SerializedName("expire")
    val expire: Long?,
    @SerializedName("last_ad_served")
    val lastAdServed: Long?
) : Serializable