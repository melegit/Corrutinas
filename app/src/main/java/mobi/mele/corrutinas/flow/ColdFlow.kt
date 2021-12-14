package mobi.mele.corrutinas.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.coroutinesmedium.getDataByFlow
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 14/12/21
 * e-mail : contact@mele.mobi
 */

fun main(){
    coldFlow()
}

fun coldFlow() {
    println("Flows are Cold")
    runBlocking {
        val dataFlow = getDataByFlow()
        println("wait for...")
        delay(someTime())
        // the flow will be suspended as long as we do not call with collect, the flow will not start.
        dataFlow.collect { println(it) }
    }
}
