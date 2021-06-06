package com.example.loginextractdetail.data.response

import com.example.loginextractdetail.base.BaseResponse
import com.example.loginextractdetail.data.api.Service

class ResponseCall() : BaseResponse() {

    suspend fun responseAuthenticateLogin(user: String, password: String): GetResponseApi {
        val response = authenticateUser(user, password)
        return responseBase(response)
    }

    suspend fun responseUserDetails(token: String): GetResponseApi {
        val response = getUserDetails(token)
        return responseBase(response)
    }

    private suspend fun authenticateUser(user: String, password: String) =
            Service.service.authenticateLogin(user, password)

    private suspend fun getUserDetails(token: String) = Service.service.getUserDetails(token)
}
