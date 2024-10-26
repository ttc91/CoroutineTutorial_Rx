package com.example.koroutinetutorial.coroutine.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.koroutinetutorial.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.java.name

    private lateinit var _btnStartActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate [IN]")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _btnStartActivity = findViewById(R.id.btn_start_activity)

        _btnStartActivity.setOnClickListener{
            //Below thread of "lifecycleScope" will not removed when destroying this activity.
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "[MainActivity - lifecycleScope] Still running...")
                    Log.d(TAG, "[MainActivity - lifecycleScope] Hello from [thread]: ${Thread.currentThread().name}")
                }
            }
            //Below thread of "GlobalScope" will not be removed when destroying this activity.
            GlobalScope.launch {
                Log.d(TAG, "[MainActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
                delay(5000L)
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            Log.d(TAG, "onCreate [OUT]")
        }

        Log.d(TAG, "[MainActivity - x]Hello from [thread]: ${Thread.currentThread().name}")

    }

}