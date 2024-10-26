package com.example.koroutinetutorial.coroutine.basic

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * <p>
 *     async { } nó cũng như runBlocking { } hay launch { } vì nó cũng được để launch 1 coroutine.
 *     Điểm khác biệt là khi sử dụng async để launch 1 coroutine thì coroutine đó cho phép bạn return
 *     về 1 giá trị kiểu Int, String, Unit, ... kiểu gì cũng được còn 2 thằng kia thì luôn return
 *     kiểu Job mà thằng Job này chỉ có thể quản lý lifecycle của coroutine chứ không mang được
 *     giá trị kết quả gì (Job does not carry any resulting value).
 * </p>
 * <p>
 *     Deferred<T>: để ý khi bạn return về kiểu Int trong khối block của coroutine thì kết quả trả về
 *     của async là kiểu Deferred<Int>, return kiểu String thì trả về kiểu Deferred<String>,
 *     không return gì cả thì nó sẽ trả về kiểu Deferred<Unit>. Deferred nó cũng giống Job vậy,
 *     nó cũng có thể quản lý lifecycle của coroutine nhưng ngon hơn thằng Job ở chỗ nó mang theo
 *     được giá trị kết quả trả về của coroutine. Và khi cần get giá trị này ra thì ta sử dụng hàm await().
 * </p>
 * <p>
 *     await(): như đã giải thích ở trên, await() là một member function của Deferred dùng để get
 *     giá trị kết quả trả về. Ví dụ biến kiểu Deferred<Int> thì gọi hàm await() sẽ trả về giá trị
 *     kiểu Int.
 * </p>
 */

fun main() = runBlocking {

    val int: Deferred<Int> = async { printInt() }
    val string: Deferred<String> = async { return@async "String" }
    val unit: Deferred<Unit> = async {  }

    println(int.await())
    println(string.await())
    println(unit.await())

}

fun printInt(): Int {
    return 99;
}