package com.example.loginextractdetail.repositories

import com.example.loginextractdetail.base.BaseRepository
import com.example.loginextractdetail.data.response.GetResponseApi
import com.example.loginextractdetail.data.response.ResponseTreatment
import org.koin.core.KoinComponent
import org.koin.core.get

open class RepositoryImplement(var responseTreatment: ResponseTreatment): BaseRepository, KoinComponent {

    init {
        responseTreatment = get<ResponseTreatment>()
    }

    override suspend fun authenticateUser(user:String,password:String): GetResponseApi {
        return responseTreatment.authenticateUser(user,password)
    }

    override suspend fun getUserDetails(token: String): GetResponseApi {
        return responseTreatment.getUserDetails(token)
    }


}