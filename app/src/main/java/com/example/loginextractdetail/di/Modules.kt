package com.example.loginextractdetail.di

import com.example.loginextractdetail.base.BaseRepository
import com.example.loginextractdetail.data.api.Service
import com.example.loginextractdetail.data.api.ServiceBuilder
import com.example.loginextractdetail.data.response.ResponseCall
import com.example.loginextractdetail.data.response.ResponseTreatment
import com.example.loginextractdetail.repositories.LoginRepository
import com.example.loginextractdetail.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module (override = true) {

    single<BaseRepository> { LoginRepository(responseTreatment = get()) }
    single { ResponseTreatment(responseCall = get()) }
    single { Service }
    single { ServiceBuilder() }
    single { ResponseCall() }
    single { ResponseTreatment(responseCall = get()) }
    viewModel { LoginViewModel(repository = get()) }

}