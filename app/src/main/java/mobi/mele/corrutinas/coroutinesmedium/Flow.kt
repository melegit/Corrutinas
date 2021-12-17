package mobi.mele.corrutinas.coroutinesmedium

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.someTime
import kotlin.random.Random

/**
 * Created by mele
 * date   : 14/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    basicFlow()
}

fun basicFlow() {
    println("Flows básicos")
    runBlocking {
        launch {
            getDataByFlow().collect { println(it) }
        }

        launch {
            (1..50).forEach {
                delay(someTime()/10)
                println("Tarea 2...")
            }
        }
    }
}

// Flow llegó para resolver aquellos casos de código asyncrono que devuelve múltiples valores
fun getDataByFlow() : Flow<Float> {
    return flow {
        (1..5).forEach {
            println("Procesando datos...")
            delay(someTime())
            // yield, se encarga de proveer un dato al consumidor, en este caso el forEach del getDAtaBySeq().forEarch
            emit(20+ it + Random.nextFloat())
        }
    }
}


