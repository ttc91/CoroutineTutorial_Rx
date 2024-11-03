package com.example.koroutinetutorial.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.koroutinetutorial.R
import com.example.koroutinetutorial.coroutine.app.FirstActivity

class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.java.name

    private lateinit var _btnStartActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate [IN]")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _btnStartActivity = findViewById(R.id.btn_start_activity)

        _btnStartActivity.setOnClickListener{
            Log.d(TAG, "_btnStartActivity.setOnClickListener[IN]")
            Intent(this@MainActivity, FirstActivity::class.java).also {
                startActivity(it)
                finish()
            }
            Log.d(TAG, "_btnStartActivity.setOnClickListener[OUT]")
        }

        Log.d(TAG, "[MainActivity - x] Hello from [thread]: ${Thread.currentThread().name}")

        Log.d(TAG, "onCreate [OUT]")
    }

}