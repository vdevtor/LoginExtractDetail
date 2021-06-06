package com.example.loginextractdetail.data.model.userextract

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Installment(
    val carnet: String,
    var detail: Detail,
    var installment: String,
    val past_due: String,
    val value: String
): Parcelable