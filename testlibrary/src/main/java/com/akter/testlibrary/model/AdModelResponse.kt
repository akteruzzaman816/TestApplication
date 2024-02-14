package com.akter.testlibrary.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Keep
data class AdModelResponse(
    @SerializedName("data") val `data`: List<Data?>?
) : Serializable

@Keep
data class Data(
    @SerializedName("imageDesktop") val imageDesktop: String?,
    @SerializedName("imageMobile") val imageMobile: String?,
    @SerializedName("title") val title: String?
) : Serializable