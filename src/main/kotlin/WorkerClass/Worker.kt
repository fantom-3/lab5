package WorkerClass

import Utils
import java.time.LocalDate
import java.time.ZonedDateTime

class Worker(
    var id: Int = Utils.generateId(),
    val name: String,
    val coordinates: Coordinates,
    var creationDate: LocalDate = Utils.generateCreationDate(),
    val salary: Long,
    val startDate: ZonedDateTime = Utils.generateStartDate(),
    var endDate: LocalDate?,
    var position: Position?,
    val person: Person
) : Comparable<Worker> {
    override fun compareTo(other: Worker): Int {
        return id.compareTo(other.id)
    }

    override fun toString(): String {
        return "Worker(id=$id, name='$name', coordinates=$coordinates, creationDate=$creationDate," +
                "salary=$salary, startDate=$startDate, endDate=$endDate, position=$position, person=$person)"
    }
}