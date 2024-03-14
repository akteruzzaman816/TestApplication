package com.akter.testlibrary.model

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Keep
data class ModelAdActionRequest(
    @SerializedName("id")
    val id: String?
) : Serializable