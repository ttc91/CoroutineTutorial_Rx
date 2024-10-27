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
import kotlinx.coroutines.runBlocking

class FifthActivity : AppCompatActivity() {

    private val TAG = FifthActivity::class.java.name

    private lateinit var _tvFifthActivity: TextView

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate [IN]")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        _tvFifthActivity = findViewById(R.id.tv_fifth_activity)

        Log.d(TAG, "[FifthActivity - x] Hello from [thread]: ${Thread.currentThread().name}")

        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "[FifthActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
            repeat(4) { i ->
                delay(800L)
                Log.d(TAG, "[FifthActivity - GlobalScope] Finish task for $i")
            }
        }

        Log.d(TAG, "[FifthActivity - x] Before <runBlocking>")

        runBlocking {

            Log.d(TAG, "[FifthActivity - x] - thread: ${Thread.currentThread().name} is blocked cause of starting coroutine")
            Log.d(TAG, "[FifthActivity - runBlocking] Hello from [thread]: ${Thread.currentThread().name}")
            job.join()
            Log.d(TAG, "[FifthActivity - x] - thread: ${Thread.currentThread().name} is unblocked cause of finishing coroutine")

        }

        Log.d(TAG, "[FifthActivity - x] After <runBlocking>")

        Log.d(TAG, "[FifthActivity - x] - thread: ${Thread.currentThread().name} is continuing")

        Log.d(TAG, "onCreate [OUT]")

    }
}