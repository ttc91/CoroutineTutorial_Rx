package com.example.koroutinetutorial.api.retrofit.data

import javax.inject.Inject

class ApiRepository @Inject constructor(private val helper: ApiHelper) {

    suspend fun getComments() = helper.getComments()

}