package com.example.loginextractdetail.data.model.userextract

data class Installment(
    val carnet: String,
    val detail: Detail,
    val installment: String,
    val past_due: String,
    val value: String
)