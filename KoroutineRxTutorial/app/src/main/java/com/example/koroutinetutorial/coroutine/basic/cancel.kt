package com.example.koroutinetutorial.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val job = launch {
        repeat(1000) { i ->
            println("coroutine: I'm sleeping in $i")
            delay(timeMillis = 500L)
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!") //main thread always firstly running in priority
    job.cancel()
    println("main: Now I can quite")

}