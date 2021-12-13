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

// definen donde queremos ejecutar los hilos de las corrutinas
// cada uno de ellos está especializado en optimizar recursos para ciertas tareas
fun dispatchers() {
    runBlocking {
        println("Dispatchers")
        launch {
            startMsg()
            println("None")
            endMsg()
        }

        // para conexiones de base de datos, locarles o remotas, escritura y lectura de archivos, o tareas de larga duración
        launch(Dispatchers.IO) {
            startMsg()
            println("IO")
            endMsg()
        }

        // para procesos que no requieren compartir datos con otras corrutinas
        launch(Dispatchers.Unconfined) {
            startMsg()
            println("Unconfined")
            endMsg()
        }

        // solo para android, para tareas muy rápidas o que están relacionadas con la interfaz de usuario
        launch(Dispatchers.Main) {
            startMsg()
            println("Main")
            endMsg()
        }

        // para tareas con uso intensivo de la CPU, procesar imagen, calculos complejos...
        launch(Dispatchers.Default) {
            startMsg()
            println("Default")
            endMsg()
        }

        // Corrutina personalizada
        launch(newSingleThreadContext("myCoroutine")) {
            startMsg()
            println("My coroutine personalized with a dispatcher")
            endMsg()
        }

        newSingleThreadContext("myCoroutineAnotherWay").use {
            launch (it){
                startMsg()
                println("My coroutine personalized with a dispatcher 2")
                endMsg()
            }
        }
    }
}
