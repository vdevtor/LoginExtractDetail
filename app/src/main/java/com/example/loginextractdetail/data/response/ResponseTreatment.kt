package com.example.loginextractdetail.data.response

import com.example.loginextractdetail.data.model.userauth.User
import org.koin.core.KoinComponent
import org.koin.core.get

class ResponseTreatment(private var responseCall: ResponseCall) : KoinComponent {

    init {
        responseCall = get<ResponseCall>()
    }

    suspend fun authenticateUser(user:String,password:String): GetResponseApi {
        val response = responseCall.responseAuthenticateLogin(user,password)

        return if (response is GetResponseApi.ResponseSuccess) {
            val data = response.data as User

            GetResponseApi.ResponseSuccess(data)
        } else {
            response
        }
    }

}