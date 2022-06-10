package com.assignment.data.model.networkmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShortFormMeaning(
    @SerializedName("lf")
    val lf: String? = null,
    @SerializedName("freq")
    val freq: Int? = null,
    @SerializedName("since")
    val since: Int? = null,
    @SerializedName("vars")
    val vars: List<ShortFormMeaning>? = null
) : Parcelable
