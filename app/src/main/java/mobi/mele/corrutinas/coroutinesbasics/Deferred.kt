package mobi.mele.corrutinas.coroutinesbasics

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 13/12/21
 * e-mail : contact@mele.mobi
 */

fun main(){
    deferred()
}

fun deferred() {
    runBlocking {
        println("Deferred")
        val deferred = async {
            startMsg()
            delay(someTime())
            println("deferred...")
            endMsg()
            multi(5,2)
            "Hola" // will always show the last value of the coroutine as a result, in this case "Hello"
        }

        println("Deferred: ${deferred}")
        println("valor del Deferred.await: ${deferred.await()}")

        // in a more direct way
        val result = async{
            multi(3,2)
        }.await()

        println("Result: $result")
    }
}

fun multi(x: Int, y: Int): Int {
    return x * y
}
