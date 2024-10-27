package com.example.koroutinetutorial.coroutine.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.koroutinetutorial.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class SixthActivity : AppCompatActivity() {

    private val TAG = SixthActivity::class.java.name

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate [IN]")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        Log.d(TAG, "[SixthActivity<onCreate> - x] Hello from [thread]: ${Thread.currentThread().name}")

        val jobFrist = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "[SixthActivity - GlobalScope<001>] Hello from [thread]: ${Thread.currentThread().name}")
            repeat(4) { i ->
                Log.d(TAG, "[SixthActivity - GlobalScope<001>] Finish task for $i")
                delay(800L)
            }
        }

        val jobSecond = GlobalScope.launch(Dispatchers.Default) {
            Log.i(TAG, "[SixthActivity - GlobalScope<002>] Hello from [thread]: ${Thread.currentThread().name}")
            repeat(4) { i ->
                delay(100L)
                Log.i(TAG, "[SixthActivity - GlobalScope<002>] Finish task for $i")
            }
        }

        Log.d(TAG, "[SixthActivity<onCreate> - x] Before <runBlocking>")

        runBlocking {
            Log.d(TAG, "[SixthActivity - runBlocking] Hello from [thread]: ${Thread.currentThread().name}")
            Log.d(TAG, "[SixthActivity - x] - thread: ${Thread.currentThread().name} is delaying within 2 seconds")
            delay(2000)
            Log.d(TAG, "[SixthActivity - x] - thread: ${Thread.currentThread().name} is continuing")
            Log.d(TAG, "[SixthActivity - x] - thread: ${Thread.currentThread().name} cancel coroutine [jobFirst]")
            jobFrist.cancel()
        }

        Log.d(TAG, "[SixthActivity<onCreate> - x] After <runBlocking>")

        Log.d(TAG, "onCreate [OUT]")

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onResume() {
        Log.w(TAG, "onResume [IN]")
        super.onResume()

        Log.w(TAG, "[SixthActivity<onResume> - x] Hello from [thread]: ${Thread.currentThread().name}")

        val jobThird = GlobalScope.launch(Dispatchers.Default) {
            Log.w(TAG, "[SixthActivity - GlobalScope<003>] Hello from [thread]: ${Thread.currentThread().name}")
            Log.w(TAG, "[SixthActivity - GlobalScope<003>] Starting running calculation...")
            withTimeout(10000L) {
                for (i in 30..40) {
                    if (isActive) {
                        Log.w(TAG, "[SixthActivity - GlobalScope<003>] Result for i = $i: ${fibonaci(i)}")
                    }
//                    Log.w(TAG, "[SixthActivity - GlobalScope<003>] Result for i = $i: ${fibonaci(i)}")
                }
            }
            Log.w(TAG, "[SixthActivity - GlobalScope<003>] Finishing running calculation...")
        }

        Log.w(TAG, "[SixthActivity<onResume> - x] Before <runBlocking>")
        runBlocking {
            Log.w(TAG, "[SixthActivity - runBlocking] Hello from [thread]: ${Thread.currentThread().name}")
            Log.w(TAG, "[SixthActivity - x] - thread: ${Thread.currentThread().name} is delaying within 2 seconds")
            delay(2000)
            Log.w(TAG, "[SixthActivity - x] - thread: ${Thread.currentThread().name} is continuing")
            Log.w(TAG, "[SixthActivity - x] - thread: ${Thread.currentThread().name} cancel coroutine [jobThird]")

            //TODO: Below coroutine will not be cancelled cause of the task of fibonaci calculating was to busy so that it does not have enough time to execute cancel command.
            jobThird.cancel()
        }
        Log.w(TAG, "[SixthActivity<onResume> - x] After <runBlocking>")

        Log.w(TAG, "onResume [OUT]")
    }

    private fun fibonaci(n: Int): Long {
        Log.v(TAG, "fibonaci($n) [IN]")
        Log.v(TAG, "fibonaci($n) is working on ${Thread.currentThread().name}")
        Log.v(TAG, "fibonaci($n) [OUT]")
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fibonaci(n - 1) + fibonaci(n - 2)
        }
    }

}