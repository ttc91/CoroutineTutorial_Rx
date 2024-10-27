package com.example.koroutinetutorial.api.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService: IService {

    companion object {
        private var api: ApiService? = null
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        private val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        fun getInstance(): ApiService {
            if (api == null) {
                api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return api!!
        }
    }

}