package mobi.mele.corrutinas.coroutinesbasics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 12/12/21
 * e-mail : contact@mele.mobi
 */

// The suspend functions wait for a result without blocking the thread in which they are executed
fun main(){
    suspendFun()
}

fun suspendFun() {
    println("Suspend")
    Thread.sleep(someTime())
    GlobalScope.launch { delay(someTime()) }
    //Suspended functions can only be called from another suspended function or a coroutine ej: delay()
}
