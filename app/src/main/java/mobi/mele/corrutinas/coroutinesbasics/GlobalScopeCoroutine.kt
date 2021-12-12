package mobi.mele.corrutinas.coroutinesbasics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by mele
 * date   : 12/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    globalScope()

    readLine()
}

fun globalScope() {
    println("Global Scope")
    GlobalScope.launch {
        println("My coroutine")
    }
}
