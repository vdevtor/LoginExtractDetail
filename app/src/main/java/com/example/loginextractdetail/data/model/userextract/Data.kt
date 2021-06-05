package com.example.loginextractdetail.data.model.userextract

data class Data(
    val installments: List<Installment>,
    val limits: Limits,
    val name: String,
    val total_due: String,
    val total_overdue: String
)