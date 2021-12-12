package mobi.mele.corrutinas

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

/**
 * Created by mele
 * date   : 11/12/21
 * e-mail : contact@mele.mobi
 */

fun main(){
    coroutinesVsThreads()
}

fun coroutinesVsThreads() {
    print("Corrutinas Vs Threads")

    //Coroutines
    //Coroutines are more efficient and do not block the program
    runBlocking {
        (1..1_000_000).forEach {
            launch{
                delay(someTime())
                print("*")
            }
        }
    }

    // Thread
    // the thread locks the program
    /*(1..1_000_000).forEach {
        thread{
            Thread.sleep(someTime())
            println("*")
        }
    }*/
}
