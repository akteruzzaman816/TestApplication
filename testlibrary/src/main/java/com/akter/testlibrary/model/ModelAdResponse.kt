package com.akter.testlibrary.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ModelAdResponse(
    @SerializedName("Advertizement")
    val advertizement: Advertizement?,
    @SerializedName("Cookies")
    val cookies: Cookies?,
    @SerializedName("Cost")
    val cost: Double?,
    @SerializedName("Key")
    val key: String?,
    @SerializedName("Token")
    val token: String?,
    @SerializedName("Tracker")
    val tracker: String?
)

@Keep
data class Advertizement(
    @SerializedName("AdString")
    val adString: String?,
    @SerializedName("Extra")
    val extra: String?,
    @SerializedName("FallBack")
    val fallBack: String?
)

@Keep
data class Cookies(
    @SerializedName("AdGroupId")
    val adGroupId: Int?,
    @SerializedName("AdsId")
    val adsId: Int?,
    @SerializedName("ad_served")
    var adServed: Int? = 0,
    @SerializedName("last_ad_served")
    var lastAdServed: Long? = 0,
    @SerializedName("Expire")
    val expire: Long?,
    @SerializedName("FlagPole")
    val flagPole: String?
)