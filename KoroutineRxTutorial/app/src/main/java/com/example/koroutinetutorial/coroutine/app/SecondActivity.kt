package com.example.koroutinetutorial.coroutine.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.koroutinetutorial.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    private val TAG = SecondActivity::class.java.name

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate [IN]")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        GlobalScope.launch {
            Log.d(TAG, "[SecondActivity - GlobalScope{001}] Hello from [thread]: ${Thread.currentThread().name}")
            val resultDoNetworkCall = doNetworkCall()
            val resultDoTask = doTask()
            delay(2000L)
            Log.d(TAG, "[SecondActivity - GlobalScope{001}] - <doNetworkCall()>: $resultDoNetworkCall")
            Log.d(TAG, "[SecondActivity - GlobalScope{001}] - <resultDoTask()>: $resultDoTask")
        }

        GlobalScope.launch {
            Log.i(TAG, "[SecondActivity - GlobalScope{002}] Hello from [thread]: ${Thread.currentThread().name}")
            val resultDoNetworkCall = doNetworkCall()
            val resultDoTask = doTask()
            Log.i(TAG, "[SecondActivity - GlobalScope{002}] - <doNetworkCall()>: $resultDoNetworkCall")
            Log.i(TAG, "[SecondActivity - GlobalScope{002}] - <resultDoTask()>: $resultDoTask")
        }

        Log.v(TAG, "[SecondActivity - x] Hello from [thread]: ${Thread.currentThread().name}")
        Log.d(TAG, "onCreate [OUT]")
    }

    suspend fun doNetworkCall(): String {
        Log.d(TAG, "doNetworkCall [IN]")
        delay(3000L)
        Log.d(TAG, "doNetworkCall [OUT]")
        return "Answer from SecondActivity-doNetworkCall()"
    }

    suspend fun doTask(): String {
        Log.d(TAG, "doTask [IN]")
        delay(3000L)
        Log.d(TAG, "doTask [OUT]")
        return "Answer from SecondActivity-doTask()"
    }

}