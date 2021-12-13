package mobi.mele.corrutinas.coroutinesbasics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/**
 * Created by mele
 * date   : 13/12/21
 * e-mail : contact@mele.mobi
 */

fun main(){
    cProduce()
}

fun cProduce() = runBlocking {
    println("Produce")
    val names = produceNames()
    names.consumeEach { println(it) }
}

fun CoroutineScope.produceNames(): ReceiveChannel<String> = produce {
    (1..5).forEach{ send("name$it") }
}
