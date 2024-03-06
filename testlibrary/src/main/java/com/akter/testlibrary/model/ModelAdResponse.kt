package com.akter.testlibrary.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Keep
data class ModelAdResponse(
    @SerializedName("Advertizement")
    val advertizement: Advertizement?,
    @SerializedName("Cookies")
    val cookies: AdCookies?,
    @SerializedName("Cost")
    val cost: Double?,
    @SerializedName("Key")
    val key: String?,
    @SerializedName("error")
    val error: String?,
    @SerializedName("Tracker")
    val tracker: String?
) : Serializable

@Keep
data class Advertizement(
    @SerializedName("AdString")
    val adString: String?,
    @SerializedName("Extra")
    val extra: String?,
    @SerializedName("FallBack")
    val fallBack: String?
) : Serializable

@Keep
data class AdCookies(
    @SerializedName("AdGroupId")
    val adGroupId: Int?,
    @SerializedName("AdsId")
    val adsId: Int?,
    @SerializedName("Expire")
    val expire: Long?,
    @SerializedName("last_ad_served")
    var lastAdServed: Long?,
    @SerializedName("ad_served")
    var adServed: Int? = 0,
    @SerializedName("FlagPole")
    val flagPole: String?
) : Serializable