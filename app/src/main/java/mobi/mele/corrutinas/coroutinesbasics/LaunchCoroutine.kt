package mobi.mele.corrutinas.coroutinesbasics

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
    cLaunch()
}

fun cLaunch() {

    //Designed specifically for tasks that do not need to return a value or result
    //It is used to trigger second tasks from which we do not need to wait for a result
    //practical example, data collection for programmer statistics, is launched at each event.

    println("Launch")
    runBlocking {
        //It can only be executed within a coroutine or suspended function.
        launch {
            startMsg()
            delay(someTime())
            println("launch")
            endMsg()
        }
    }
}
