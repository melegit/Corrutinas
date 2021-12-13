package mobi.mele.corrutinas.coroutinesmedium

import kotlinx.coroutines.*
import mobi.mele.corrutinas.coroutinesbasics.endMsg
import mobi.mele.corrutinas.coroutinesbasics.startMsg
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 13/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    nestedCoroutine()
}

fun nestedCoroutine() {
    runBlocking {
        println("Nested Coroutines")

        val job = launch {
            startMsg()

            launch {
                startMsg()
                delay(someTime())
                println("Another task")
            }

            val jobIO = launch(Dispatchers.IO) {
                startMsg()

                launch(newSingleThreadContext("personalized coroutine")) {
                    startMsg()
                    println("Coroutine personalized")
                    endMsg()
                }

                delay(someTime())
                println("Task in the server")

            }

            delay(someTime())
            jobIO.cancel()
            println("Job IO cancelled...")

            var sum = 0
            (1..100).forEach{
                sum += it
                delay(someTime()/100)
            }
            println("Sum = ${sum}")
            endMsg()
        }

        delay(someTime()/2)
        // When you cancel the parent routine, all the children that have not finished are canceled
        job.cancel()
        println("Job cancelled ...")
    }
}
