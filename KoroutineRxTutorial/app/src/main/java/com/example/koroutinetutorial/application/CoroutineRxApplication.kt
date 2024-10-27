package com.example.koroutinetutorial.application

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoroutineRxApplication: Application() {

    private val TAG = CoroutineRxApplication::class.java.name

    override fun onCreate() {
        Log.d(TAG, "onCreate [IN]")
        super.onCreate()
        Log.d(TAG, "onCreate [OUT]")
    }

}