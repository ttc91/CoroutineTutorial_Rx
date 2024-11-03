package com.example.koroutinetutorial.api.retrofit.data

import com.example.koroutinetutorial.api.model.Comment
import io.reactivex.rxjava3.core.Flowable

interface IHelper {

    suspend fun getComments(): Flowable<Comment>

}