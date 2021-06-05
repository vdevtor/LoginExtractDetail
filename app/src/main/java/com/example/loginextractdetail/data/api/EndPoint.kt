package com.example.loginextractdetail.data.api

import com.example.loginextractdetail.data.model.userauth.User
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query


interface EndPoint {
    @POST("login.php?")
    suspend fun authenticateLogin(@Query("usr") user: String,
                                  @Query("pwd") password: String): Response<User>
}