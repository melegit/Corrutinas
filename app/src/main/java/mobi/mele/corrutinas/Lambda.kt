package mobi.mele.corrutinas

fun main(){
lambda()
}

fun lambda() {
    println(multi(2,3))

    //1 Option
    println(multiLambda(2, 3, {
        result ->
        println(result)
    }))

    //2 Option
    multiLambda(2, 3) {
            result ->
        println(result)
    }
}

fun multiLambda(x: Int, y: Int, callback: (result: Int)->Unit){
    callback(x * y)
}

fun multi(x: Int, y: Int): Int {
    return x * y
}
