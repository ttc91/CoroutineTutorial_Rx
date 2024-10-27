package com.example.koroutinetutorial.coroutine.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.koroutinetutorial.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstActivity : AppCompatActivity() {

    private val TAG = FirstActivity::class.java.name

    private lateinit var _btnSecondActivity: Button
    private lateinit var _btnThirdActivity: Button
    private lateinit var _btnFourthActivity: Button
    private lateinit var _btnFifthActivity: Button
    private lateinit var _btnSixthActivity: Button
    private lateinit var _btnSeventhActivity: Button


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate [IN]")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        _btnSecondActivity = findViewById(R.id.btn_second_activity)
        _btnThirdActivity = findViewById(R.id.btn_third_activity)
        _btnFourthActivity = findViewById(R.id.btn_fourth_activity)
        _btnFifthActivity = findViewById(R.id.btn_fifth_activity)
        _btnSixthActivity = findViewById(R.id.btn_sixth_activity)
        _btnSeventhActivity = findViewById(R.id.btn_seventh_activity)

        _btnSecondActivity.setOnClickListener{
            Log.d(TAG, "_btnSecondActivity.setOnClickListener [IN]")
            //Below thread of "lifecycleScope" will not removed when destroying this activity.
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "[FirstActivity - lifecycleScope] Still running...")
                    Log.d(TAG, "[FirstActivity - lifecycleScope] Hello from [thread]: ${Thread.currentThread().name}")
                }
            }
            //Below thread of "GlobalScope" will not be removed when destroying this activity.
            GlobalScope.launch {
                Log.d(TAG, "[FirstActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
                delay(5000L)
                Intent(this@FirstActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            Log.d(TAG, "_btnSecondActivity.setOnClickListener [OUT]")
        }

        _btnThirdActivity.setOnClickListener {
            Log.d(TAG, "_btnThirdActivity.setOnClickListener [IN]")

            lifecycleScope.launch {
                Log.d(TAG, "[FirstActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
                delay(5000L)
                Intent(this@FirstActivity, ThirdActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            Log.d(TAG, "_btnThirdActivity.setOnClickListener [OUT]")
        }

        _btnFourthActivity.setOnClickListener {
            Log.d(TAG, "_btnFourthActivity.setOnClickListener [IN]")

            lifecycleScope.launch {
                Log.d(TAG, "[FirstActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
                delay(5000L)
                Intent(this@FirstActivity, FourthActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            Log.d(TAG, "_btnFourthActivity.setOnClickListener [OUT]")
        }

        _btnFifthActivity.setOnClickListener {
            Log.d(TAG, "_btnFifthActivity.setOnClickListener [IN]")

            lifecycleScope.launch {
                Log.d(TAG, "[FirstActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
                delay(5000L)
                Intent(this@FirstActivity, FifthActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            Log.d(TAG, "_btnFifthActivity.setOnClickListener [OUT]")
        }

        _btnSixthActivity.setOnClickListener {
            Log.d(TAG, "_btnSixthActivity.setOnClickListener [IN]")

            lifecycleScope.launch {
                Log.d(TAG, "[FirstActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
                delay(5000L)
                Intent(this@FirstActivity, SixthActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            Log.d(TAG, "_btnSixthActivity.setOnClickListener [OUT]")
        }

        _btnSeventhActivity.setOnClickListener {
            Log.d(TAG, "_btnSeventhActivity.setOnClickListener [IN]")

            lifecycleScope.launch {
                Log.d(TAG, "[FirstActivity - GlobalScope] Hello from [thread]: ${Thread.currentThread().name}")
                delay(5000L)
                Intent(this@FirstActivity, SeventhActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            Log.d(TAG, "_btnSeventhActivity.setOnClickListener [OUT]")
        }

        Log.d(TAG, "[MainActivity - x] Hello from [thread]: ${Thread.currentThread().name}")
        Log.d(TAG, "onCreate [OUT]")
    }
}