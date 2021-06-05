package com.example.loginextractdetail.data.model.userauth

import com.example.loginextractdetail.data.model.userauth.Data

data class User(
        val code: Int,
        val `data`: Data?,
        val status: String
)