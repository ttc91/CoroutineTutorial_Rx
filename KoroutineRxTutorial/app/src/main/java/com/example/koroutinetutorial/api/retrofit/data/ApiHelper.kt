package com.example.koroutinetutorial.api.retrofit.data

import android.util.Log
import com.example.koroutinetutorial.api.model.Comment
import com.example.koroutinetutorial.api.retrofit.ApiService
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class ApiHelper @Inject constructor(private val service: ApiService): IHelper {

    private val TAG = ApiHelper::class.java.name

    private var response: MutableList<Comment> = ArrayList()

    override suspend fun getComments(): Flowable<Comment> {
        Log.d(TAG, "getComments [IN]")
        coroutineScope {
            Log.d(TAG, "coroutineScope [IN]")
            val comments = service.getComments()
            response = comments.toMutableList()
            Log.d(TAG, "coroutineScope [OUT]")
        }
        val result: Flowable<Comment> =
            Flowable.fromIterable(response)
        Log.d(TAG, "getComments [OUT]")
        return result
    }

}