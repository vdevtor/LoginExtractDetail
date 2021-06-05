package com.example.loginextractdetail.data.model.userextract

data class Detail(
    val description: String,
    val images: List<String>,
    val name: String,
    val original_value: String,
    val overdue_date: String,
    val overdue_days: String,
    val store: String,
    val total_value: String,
    val value_diff: String
)