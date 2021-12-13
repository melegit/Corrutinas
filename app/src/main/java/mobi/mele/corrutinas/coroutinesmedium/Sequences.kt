package mobi.mele.corrutinas.coroutinesmedium

import mobi.mele.corrutinas.someTime
import kotlin.random.Random

/**
 * Created by mele
 * date   : 14/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    sequences()
}

fun sequences() {
    println("Sequences")
    getDataBySeq().forEach { println("${it}") }
}

// Colección que se encarga de devolver los valores por pasos, tiene la característica de catalogarse como perezosa(Lazy)
// No devuelve la colleción en una sola ación sino que lo hace a medida que se va solicitando, en este caso el consumidor o quien hace las
// solicitudes es el forEach de la llamada al método.

fun getDataBySeq() : Sequence<Float> {
    return sequence {
        (1..5).forEach {
            println("Procesando datos...")
            Thread.sleep(someTime())
            // yield, se encarga de proveer un dato al consumidor, en este caso el forEach del getDAtaBySeq().forEarch
            yield(20+ it + Random.nextFloat())
        }
    }
}
