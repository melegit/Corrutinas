package mobi.mele.corrutinas.coroutinesmedium

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.coroutinesbasics.endMsg
import mobi.mele.corrutinas.coroutinesbasics.startMsg

/**
 * Created by mele
 * date   : 13/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    dispatchers()
}

// define the contexts where we want to execute the coroutines
// each of them is specialized in optimizing resources for certain tasks
fun dispatchers() {
    runBlocking {
        println("Dispatchers")
        launch {
            startMsg()
            println("None")
            endMsg()
        }

        // for local or remote database connections, file reading and writing, or long-running tasks
        launch(Dispatchers.IO) {
            startMsg()
            println("IO")
            endMsg()
        }

        // for processes that do not require data sharing with other coroutines
        launch(Dispatchers.Unconfined) {
            startMsg()
            println("Unconfined")
            endMsg()
        }

        // only for android, for very fast tasks or that are related to the user interface
        launch(Dispatchers.Main) {
            startMsg()
            println("Main")
            endMsg()
        }

        // for CPU intensive tasks, image processing, complex calculations ...
        launch(Dispatchers.Default) {
            startMsg()
            println("Default")
            endMsg()
        }

        // Custom context for coroutine
        launch(newSingleThreadContext("myCoroutine")) {
            startMsg()
            println("My coroutine personalized with a dispatcher")
            endMsg()
        }

        // Custom context for coroutine
        newSingleThreadContext("myCoroutineAnotherWay").use {
            launch (it){
                startMsg()
                println("My coroutine personalized with a dispatcher 2")
                endMsg()
            }
        }
    }
}
