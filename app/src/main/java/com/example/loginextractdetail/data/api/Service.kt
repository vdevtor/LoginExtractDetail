package com.example.loginextractdetail.data.api

import org.koin.core.KoinComponent
import org.koin.core.get

object Service : KoinComponent {

    private val builder = get<ServiceBuilder>()
    val service: EndPoint = builder.getEndPointClient().create(EndPoint::class.java)
}