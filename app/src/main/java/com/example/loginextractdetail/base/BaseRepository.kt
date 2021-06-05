package com.example.loginextractdetail.base

import com.example.loginextractdetail.data.response.GetResponseApi

interface BaseRepository {

    suspend fun authenticateUser(user:String,password:String): GetResponseApi
}