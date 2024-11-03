package com.example.koroutinetutorial.api.retrofit.ui

import androidx.lifecycle.ViewModel
import com.example.koroutinetutorial.api.retrofit.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

}