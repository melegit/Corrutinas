package mobi.mele.corrutinas.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.coroutinesmedium.getDataByFlow
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 14/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    cancelFlow()
}
// if the coroutine is canceled, the contained flow is canceled
fun cancelFlow() {
    runBlocking {
        println("--------Cancelled Flow-----------")
        val job = launch {
            getDataByFlow().collect { println(it) }
        }
        delay(someTime()*2)
        // the flow is canceled before finishing completely
        job.cancel()
        println("flow is Cancelled?: ${job.isCancelled}")
    }
}
