package com.example.koroutinetutorial.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main(): Unit = runBlocking {
//
//    launch {
//        println("Task from runBlocking")
//    }
//
//    coroutineScope {
//
//        launch {
//            println("Task from nested launch coroutine scope")
//        }
//
//        println("Task from coroutine scope")
//
//    }
//
//    println("Coroutine scope is over ")
//
//}

fun main() = runBlocking {
    val request = launch {
        // it spawns two other jobs, one with GlobalScope
        GlobalScope.launch {
            println("job1: GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation")  // line code 1 này vẫn được in ra mặc dù bị delay 1000ms
        }
        // and the other inherits the parent context
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")
}