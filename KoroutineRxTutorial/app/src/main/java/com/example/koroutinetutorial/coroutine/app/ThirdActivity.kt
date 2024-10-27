package com.example.koroutinetutorial.coroutine.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.koroutinetutorial.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThirdActivity : AppCompatActivity() {

    private val TAG = ThirdActivity::class.java.name

    private lateinit var _tvThirdActivity: TextView

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate [IN]")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        _tvThirdActivity = findViewById(R.id.tv_third_activity)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "[ThirdActivity - GlobalScope{Dispatchers.IO}] Hello from [thread]: ${Thread.currentThread().name}")
            val  resultDoNetworkCall = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "[ThirdActivity - GlobalScope{Dispatchers.Main}] Hello from [thread]: ${Thread.currentThread().name}")
                Log.d(TAG, "[ThirdActivity - GlobalScope{Dispatchers.Main}] Setting text from [thread]: ${Thread.currentThread().name}")
                _tvThirdActivity.text = resultDoNetworkCall
            }
        }

        Log.d(TAG, "[ThirdActivity - x] Hello from [thread]: ${Thread.currentThread().name}")
        Log.d(TAG, "onCreate [OUT]")
    }

    suspend fun doNetworkCall(): String {
        Log.d(TAG, "doNetworkCall [IN]")
        delay(3000L)
        Log.d(TAG, "doNetworkCall [OUT]")
        return "Answer from SecondActivity-doNetworkCall()"
    }
}