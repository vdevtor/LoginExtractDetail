package com.example.loginextractdetail.data.model.userextract

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserDataInfo(
    var installments: List<Installment>?,
    val limits: Limits,
    val name: String,
    @SerializedName("total_due")
    val totalDue: String,
    @SerializedName("total_overdue")
    val totalOverdue: String
): Parcelable