package com.example.koroutinetutorial.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// launch a new coroutine and keep a reference to its Job
fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(5000L)
        println("World")
    }
    println("Hello")
    job.join()
    println("Kotlin")
}