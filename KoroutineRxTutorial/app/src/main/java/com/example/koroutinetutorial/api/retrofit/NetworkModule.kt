package com.example.koroutinetutorial.api.retrofit

import android.util.Log
import com.example.koroutinetutorial.api.retrofit.data.ApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val TAG = NetworkModule::class.java.name

    private lateinit var _mApiHelper: ApiHelper

    init {
        Log.d(TAG, "init [IN]")
        _mApiHelper = ApiHelper(ApiService.getInstance())
        Log.d(TAG, "init [OUT]")
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        Log.d(TAG, "provideApiService [IN]")
        Log.d(TAG, "provideApiService [OUT]")
        return ApiService.getInstance()
    }

    @Singleton
    @Provides
    fun provideApiHelper(): ApiHelper {
        Log.d(TAG, "provideApiHelper [IN]")
        Log.d(TAG, "provideApiHelper [OUT]")
        return _mApiHelper
    }

}