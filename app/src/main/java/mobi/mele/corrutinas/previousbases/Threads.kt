package mobi.mele.corrutinas

import kotlin.concurrent.thread
import kotlin.random.Random

/**
 * Created by mele
 * date   : 8/12/21
 * e-mail : contact@mele.mobi
 */

    fun main(){
        threads()
    }

    fun threads() {
        println(multiThread(2,3))
    }

    fun multiThread(x: Int, y: Int): Int {
        var result = 0

        thread{
            Thread.sleep(someTime())
            result = x * y
        }

        // only by exceeding the ramdon time of the server(fun someTime) we obtain the value of the multiplication by console
        // otherwise a 0 will be printed
        // Thread.sleep(2_000)
        return result
    }

    // mocked server response time
    fun someTime(): Long = Random.nextLong(500,2_000)