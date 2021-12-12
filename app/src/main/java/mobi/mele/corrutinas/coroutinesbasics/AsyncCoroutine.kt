package mobi.mele.corrutinas.coroutinesbasics

import kotlinx.coroutines.async
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
    cAsync()

    readLine()
}

fun cAsync() {

    //Unlike launch, designed specifically for tasks that need to return a value or result

    println("Launch")
    runBlocking {
        //Para ser usada
        val result = async {
            startMsg()
            delay(someTime())
            println("launch")
            endMsg()
            1 // Value that our coroutine will return with the await () method
        }
        println("Result: ${result.await()}")
    }
}