package mobi.mele.corrutinas.coroutinesbasics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 12/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    cRunBlocking()
}
// runBlocking -> for educational and testing purposes only
// the context is equal to that of globalScope.launch -> CoroutineScope

fun cRunBlocking() {
    println("RunBlocking")
    //Suspend the thread until the code block ends
    runBlocking {
        startMsg()
        delay(someTime())
        println("runBlocking...")
        endMsg()
    }
}

fun endMsg() {
    println("Begin Coroutine -${Thread.currentThread().name}-")
}

fun startMsg() {
    println("Coroutine -${Thread.currentThread().name}- end")
}
