package mobi.mele.corrutinas.coroutinesbasics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 13/12/21
 * e-mail : contact@mele.mobi
 */

fun main(){
    job()
}

fun job() {
    runBlocking {
        println("Job")
        // A Job is the life cycle of a coroutine, it is a background job that can be canceled.
        val job = launch{
            startMsg()
            delay(2_100)
            println("job...")
            endMsg()
        }

        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isCompleted: ${job.isCompleted}")

        delay(someTime())
        println("Tarea cancelada o interrumpida")
        job.cancel()

        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isCompleted: ${job.isCompleted}")
    }
}
