package com.adobe.finalProject.setup

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val networkModule = module {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    single {
        Retrofit.Builder().baseUrl("https://api.nytimes.com/").client(
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        ).addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create()).build()
    }
}