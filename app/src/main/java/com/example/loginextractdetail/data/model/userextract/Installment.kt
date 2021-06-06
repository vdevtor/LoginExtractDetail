package com.example.loginextractdetail.data.model.userextract

data class Installment(
    val carnet: String,
    var detail: Detail,
    var installment: String,
    val past_due: String,
    val value: String
)