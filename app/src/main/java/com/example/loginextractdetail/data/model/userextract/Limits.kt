package com.example.loginextractdetail.data.model.userextract

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Limits(
    val available: String,
    val expent: String,
    val total: String,
    @SerializedName("total_due")
    val totalDue: String
):Parcelable