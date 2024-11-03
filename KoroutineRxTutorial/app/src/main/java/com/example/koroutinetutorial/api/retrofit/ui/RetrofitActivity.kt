package com.example.koroutinetutorial.api.retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.koroutinetutorial.databinding.ActivityRetrofitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {

    private val TAG = RetrofitActivity::class.java.name

    private lateinit var _mBinding: ActivityRetrofitBinding

    private val _mVM: RetrofitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate [IN]")

        _mBinding = ActivityRetrofitBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(_mBinding.root)



        Log.d(TAG, "onCreate [OUT]")
    }

}