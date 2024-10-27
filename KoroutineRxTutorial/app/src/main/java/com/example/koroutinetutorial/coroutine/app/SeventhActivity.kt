package com.example.koroutinetutorial.coroutine.app

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.koroutinetutorial.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class SeventhActivity : AppCompatActivity() {

    private val TAG = SeventhActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate [IN]")

        Log.v(TAG, "[SeventhActivity - x] Hello from [thread]: ${Thread.currentThread().name}")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seventh)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "[SeventhActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
            val timeFrist = measureTimeMillis {
                //===== Below logical of code will not be executed at the same time.
                val ipJson = networkIPCall()
                val macJson = networkMACCall()
                Log.d(TAG, "[SeventhActivity - GlobalScope] Result for IP: $ipJson")
                Log.d(TAG, "[SeventhActivity - GlobalScope] Result for MAC: $macJson")
            }
            Log.d(TAG, "[SeventhActivity - GlobalScope] Execution timeFrist: $timeFrist")

            val timeSecond = measureTimeMillis {
                //===== Below logical of code will be executed at the same time but bad practice.
                var ipJson: String? = null
                var macJson: String? = null

                val jobFirst = launch {
                    Log.d(TAG, "[SeventhActivity - GlobalScope.launch<001>] Hello from [thread]: ${Thread.currentThread().name}")
                    ipJson = networkIPCall()
                }

                val jobSecond = launch {
                    Log.d(TAG, "[SeventhActivity - GlobalScope.launch<002>] Hello from [thread]: ${Thread.currentThread().name}")
                    macJson = networkMACCall()
                }

                jobFirst.join()
                jobSecond.join()

                Log.d(TAG, "[SeventhActivity - GlobalScope] Result for IP: $ipJson")
                Log.d(TAG, "[SeventhActivity - GlobalScope] Result for MAC: $macJson")
            }
            Log.d(TAG, "[SeventhActivity - GlobalScope] Execution timeSecond: $timeSecond")

            val timeThird = measureTimeMillis {
                //===== Below logical of code will be executed at the same time with best practice.
                val ipJson = async {
                    Log.d(TAG, "[SeventhActivity - GlobalScope.async<001>] Hello from [thread]: ${Thread.currentThread().name}")
                    networkIPCall()
                }
                val macJson= async {
                    Log.d(TAG, "[SeventhActivity - GlobalScope.async<002>] Hello from [thread]: ${Thread.currentThread().name}")
                    networkMACCall()
                }

                Log.d(TAG, "[SeventhActivity - GlobalScope] Result for IP: ${ipJson.await()}")
                Log.d(TAG, "[SeventhActivity - GlobalScope] Result for MAC: ${macJson.await()}")
            }
            Log.d(TAG, "[SeventhActivity - GlobalScope] Execution timeThird: $timeThird")
        }


        Log.d(TAG, "onCreate [OUT]")
    }

    private suspend fun networkIPCall(): String {
        Log.d(TAG, "networkCall [IN]")
        delay(1000L)
        Log.d(TAG, "networkCall [OUT]")
        return "API: network: {10.0.0.0}"
    }

    private suspend fun networkMACCall(): String {
        Log.d(TAG, "networkCall [IN]")
        Log.d(TAG, "networkCall is working with ${Thread.currentThread().name}")
        delay(1000L)
        Log.d(TAG, "networkCall [OUT]")
        return "API: mac: {AB:CD:EF:TT}"
    }

}