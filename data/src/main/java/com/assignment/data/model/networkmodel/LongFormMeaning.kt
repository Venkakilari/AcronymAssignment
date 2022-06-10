package com.assignment.data.model.networkmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LongFormMeaning(
    @SerializedName("lf")
    val sf: String? = null,
    @SerializedName("freq")
    val freq: Int? = null,
    @SerializedName("since")
    val since: Int? = null,
    @SerializedName("vars")
    val vars: List<LongFormMeaning>? = null
) : Parcelable
