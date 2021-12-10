package mobi.mele.corrutinas

import kotlin.concurrent.thread

/**
 * Created by mele
 * date   : 10/12/21
 * e-mail : contact@mele.mobi
 */

fun main(){
    threadsPlusLambda()
}

fun threadsPlusLambda() {
    multiThreadLambda(2, 3){ result ->
        println("Thread+Lambda ${result}")
    }
}

// when result is executed the multiplication operation, the function returns the result by callback
fun multiThreadLambda(x: Int, y: Int, callback: (result: Int) -> Unit) {
    var result = 0

    thread{
        Thread.sleep(someTime())
        result = x * y
        callback(result)
    }
}

