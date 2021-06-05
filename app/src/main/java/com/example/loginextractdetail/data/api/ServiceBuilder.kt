package com.example.loginextractdetail.data.api


import com.example.loginextractdetail.utils.Constants.BASE_PWD_NAME
import com.example.loginextractdetail.utils.Constants.BASE_PWD_VALUE
import com.example.loginextractdetail.utils.Constants.BASE_URL
import com.example.loginextractdetail.utils.Constants.BASE_USER_NAME
import com.example.loginextractdetail.utils.Constants.BASE_USER_VALUE
import com.example.loginextractdetail.utils.Constants.DEFAULT_TIMEOUT_SECONDS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceBuilder {

    fun getEndPointClient(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(getInterceptorClient())
                .build()
    }

    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .callTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                            .addHeader(BASE_USER_NAME, BASE_USER_VALUE)
                            .addHeader(BASE_PWD_NAME, BASE_PWD_VALUE)
                            .build()
                    chain.proceed(newRequest)
                }.addInterceptor {
                    chain ->
                    val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .build()

                    val newRequest = chain.request()
                            .newBuilder()
                            .url(url)
                            .build()
                    chain.proceed(newRequest)
                }

        return interceptor.build()
    }

}