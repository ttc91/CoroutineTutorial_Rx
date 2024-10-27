package com.example.koroutinetutorial.api.retrofit

import com.example.koroutinetutorial.api.model.Comment
import retrofit2.http.GET

interface IService {

    @GET("comments")
    suspend fun getComments(): List<Comment>



}