package com.example.koroutinetutorial.coroutine.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.koroutinetutorial.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FourthActivity : AppCompatActivity() {

    private val TAG = FourthActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate [IN]")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        Log.d(TAG, "[FourthActivity - x] Hello from [thread]: ${Thread.currentThread().name}")

        Log.d(TAG, "[FourthActivity - x] Before <runBlocking>")
        runBlocking {

            Log.d(TAG, "[FourthActivity - runBlocking] Hello from [thread]: ${Thread.currentThread().name}")
            Log.d(TAG, "[FourthActivity - runBlocking] start")

            launch(Dispatchers.IO) {
                Log.d(TAG, "[FourthActivity - runBlocking<Dispatchers.IO>] Hello from [thread]: ${Thread.currentThread().name}")
                Log.d(TAG, "[FourthActivity - runBlocking<Dispatchers.IO>] Finished 001")
            }

            launch(Dispatchers.IO) {
                Log.d(TAG, "[FourthActivity - runBlocking<Dispatchers.IO>] Hello from [thread]: ${Thread.currentThread().name}")
                Log.d(TAG, "[FourthActivity - runBlocking<Dispatchers.IO>] Finished 002")
            }

            Log.d(TAG, "[FourthActivity - runBlocking] delaying 5 seconds")
            delay(5000L)
            Log.d(TAG, "[FourthActivity - runBlocking] finished with delaying 5 seconds")

            Log.d(TAG, "[FourthActivity - runBlocking] end")
        }
        Log.d(TAG, "[FourthActivity - x] After <runBlocking>")

        Log.d(TAG, "onCreate [OUT]")
    }
}