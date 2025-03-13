package WorkerClass

class Coordinates(
    val x: Long, val y: Double
) {
    init{
        require(x <= 42) {"Координата x не должна быть больше 42"}
        require(y <= 431) {"Координата y не должна быть больше 431"}
    }
}