package mobi.mele.corrutinas.coroutinesmedium

import kotlinx.coroutines.*
import mobi.mele.corrutinas.coroutinesbasics.endMsg
import mobi.mele.corrutinas.coroutinesbasics.startMsg
import mobi.mele.corrutinas.someTime

/**
 * Created by mele
 * date   : 14/12/21
 * e-mail : contact@mele.mobi
 */

fun main(){
    changeWithContext()
}

// with withContext we do not change or create new coroutines, we only change the context where the current coroutine or code is executing
fun changeWithContext() {
    runBlocking {
        println("withContext")
        startMsg()

        withContext(newSingleThreadContext("change coroutine's context")){
            startMsg()
            delay(someTime())
            println("Change Context")
            endMsg()
        }

        withContext(Dispatchers.IO){
            startMsg()
            delay(someTime())
            println("Change Context = request to the server")
            endMsg()
        }

        endMsg()
    }
}
