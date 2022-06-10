package com.assignment.data.model.networkmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AcronymResponse(
    @SerializedName("sf")
    val sf: String? = null,
    @SerializedName("lfs")
    val lfs: List<ShortFormMeaning>? = null
) : Parcelable
