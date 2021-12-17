package mobi.mele.corrutinas.flow

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import mobi.mele.corrutinas.coroutinesmedium.getDataByFlow
import java.lang.Exception
import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.text.Typography.degree

/**
 * Created by mele
 * date   : 14/12/21
 * e-mail : contact@mele.mobi
 */
fun main(){
    //flowOperators()
    //terminalFlowOperators()
    //bufferFlow()
    //conflateFlow()
    //multiFlow()
    //flatFlows()
    //flowExceptions()
    //completions()
    //cancelableFlow()
}

fun completions() {
    runBlocking {
        println("Fin de un flujo(on Completion)")
        getCitiesFlow()
            .onCompletion { println("Quitar el progressBar ...") }
            //.collect { println(it) }
        println()

        getMatchResultsFlow()
            .onCompletion { println("Mostrar las estadísticas...") }
            .catch { emit("Error: $this") }
            .collect { println(it) }

        println("Cancelar Flow")

        getDataByFlowStatic()
            .onCompletion { println("ya no le interesa al usuario...") }
            .cancellable()
            .collect {
                if(it > 22.5f) cancel()
                println(it) }
    }
}

fun flowExceptions() {
    runBlocking {
        println("Control de Errores")
/*        println("Try/Catch")
        try {
            getMatchResultsFlow()
                .collect {
                    println(it)
                    if (it.contains("2")) throw Exception("Habían acordado 1-1 : V")
                }
        }catch (e: Exception){
            e.printStackTrace()
        }*/

        println("Transparencia")
        getMatchResultsFlow()
            .catch {
                emit("Error : $this")
            }
            .collect {
                println(it)
                if(!it.contains("-")) println("Notifica al programador...")
            }
    }

}

fun flatFlows(){
    runBlocking{
        println("Flujos de aplanamiento")

        println("FlatMapConcat")
        getCitiesFlow()
            .flatMapConcat { city-> //Flow<Flow<T>>
                getDataToFlatFlow(city)
            }
            .map { setFormat(it) }
            .collect { println(it) }

        println("FlatMapMerge")
        getCitiesFlow()
            .flatMapMerge { city-> //Flow<Flow<T>>
                getDataToFlatFlow(city)
            }
            .map { setFormat(it) }
            .collect { println(it) }
    }
}

fun getDataToFlatFlow(city: String) : Flow<Float> = flow {
    (1..3).forEach {
        println("Temperatura de ayer en $city...")
        emit(Random.nextInt(10, 30).toFloat())

        println("Temperatura actual en $city: ")
        delay(100)
        emit(20 + it + Random.nextFloat())
    }
}

fun getCitiesFlow(): Flow<String> = flow {
    listOf("Santander", "CDMX", "Lima")
        .forEach { city ->
            println("\nConsultando ciudad...")
            delay(1_000)
            emit(city)
        }
}

fun multiFlow() {
    runBlocking {
        println("Zip & Combine")
        getDataByFlowStatic()
            .map { setFormat(it) }
            //.combine(getMatchResultsFlow()){ // no esta limitado por el flujo menor
            .zip(getMatchResultsFlow()){ //combina resultado de los dos flows pero está termina cuando termine el flow de menor operaciones
                degree, result ->
                "$result with $degree"
            }
            .collect { println(it) }
    }
}

fun conflateFlow() {
    runBlocking {
        println("------- Fusión --------")
        val time = measureTimeMillis {
            getMatchResultsFlow()
                .conflate() //2662 ms solo devuelve el último resultado que haya según nuestro delay de 100ms, por lo que como en la funcion getMatchResultsFlow el dealy es de 50, no devolverá nada hasta la tercera vez ((1)50, + (2)50 =100, +(3)50)
                //.buffer() //4809 ms
                //.collectLatest { // collectLastest solo para devolver el resultado final, aquellos escenarios de informenes o resumes de datos
                .collect { //7143 ms
                    delay(100)
                    println(it)
                }
        }
        println("Time: ${time}ms")
    }
}

fun getMatchResultsFlow(): Flow<String> {
    return flow{
        var homeTeam = 0
        var awayTeam = 0
        (0..45).forEach {
            println("minute: $it")
            delay(50)
            //solo cuando el random sea 21/20 o 20/20 sumará uno al resultado
            homeTeam += Random.nextInt(0, 21) / 20
            awayTeam += Random.nextInt(0, 21) / 20
            emit("$homeTeam-$awayTeam")

            if(homeTeam == 2 || awayTeam == 2) throw Exception("Habían acordado 1 y 1 : v")
        }
    }
}

fun bufferFlow() {
    runBlocking {
        println("Buffer para flow")
        val time = measureTimeMillis {
            getDataByFlowStatic()
                .map { setFormat(it) }
                .buffer()
                .collect {
                    delay(500)
                    println(it)
                }
        }
        println("Time: ${time}ms")
    }
}

fun getDataByFlowStatic() : Flow<Float> {
    return flow{
        (1..5).forEach {
            println("Procesando datos...")
            delay(300)
            emit(20 + it + Random.nextFloat())
        }
    }
}

fun terminalFlowOperators() {
    runBlocking {
        println("--------Terminal Flow Operators--------")
        // Uso aconsejado de list cuando el procesamiento de los datos no requiera de mucho tiempo
        // porque va creando la lista a medida que los datos van llegando.
        println("--------List--------")
        val list = getDataByFlow()
            //.toList()
        println("List : $list")

        // Usamos single cuando en lugar de una lista limitamos a un solo item
        println("--------Single--------")
        val single = getDataByFlow()
            //.take(1)
            //.single()
        println("single : $single")

        // Usamos first cuando queremos el primer item procesado de la colección
        println("--------First--------")
        val first = getDataByFlow()
        //.first()
        println("First : $first")

        // Usamos Last cuando queremos el último item procesado de la colección
        // en este caso al contrario que first se procesarán todos los datos de la colección pero solo accederemos al último.
        println("--------Last--------")
        val last = getDataByFlow()
        //.last()
        println("Last : $last")

        // Nos da un solo valor, se puede usar para el sumatorio de una sucesión.
        println("--------Reduce--------")
        val saving = getDataByFlow()
            .reduce { accumulator, value ->
                println("Accumulator: $accumulator")
                println("Value: $value")
                println("Current saving: ${accumulator + value}")
                accumulator + value
            }
        println("Reduce : $saving")

        // nos permite implementar un reduce pero con el acumulador que queramos
        println("--------Fold--------")
        val lastSaving = saving
        val totalSaving = getDataByFlow().fold(lastSaving, { acc, value ->
            println("Accumulador: $acc")
            println("Value: $value")
            println("Current saving: $value")
            acc + value
        })
        println("Fold : $totalSaving")
    }
}

fun flowOperators() {
    runBlocking {
        println("-----------Intermediate Flow Operators-----------")
        println("-----------Map-----------")
        getDataByFlow()
            .map {
                setFormat(it)
                // solo se procesa lo último sobre los datos
                setFormat(converCelsToFahr(it), "F")
            }
            //.collect { println(it) }
        println("-----------Filter-----------")
        getDataByFlow()
            .filter {
                it < 23
            }.map {
                setFormat(it)
            }
            //.collect { println(it) }

        // Para multiples procesamientos
        println("-----------Transform-----------")
        getDataByFlow()
            .transform {
                emit(setFormat(it))
                emit(setFormat(converCelsToFahr(it),"F"))
            }//.collect { println(it) }

        // limita los resultados al número indicado con take
        println("-----------Take-----------")
        getDataByFlow()
            .take(3)
            .map{ setFormat(it)}
            .collect { println(it) }
    }
}

fun converCelsToFahr(cels: Float): Float = ( (cels * 9) / 5 ) + 32

fun setFormat(temp: Float, degree: String = "C"): String = String.format(Locale.getDefault(), "%.1fº$degree", temp)
