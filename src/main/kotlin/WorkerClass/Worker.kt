package WorkerClass

import Utils
import java.time.LocalDate
import java.time.ZonedDateTime

class Worker(
    var id: Int = Utils.generateId(),
    var name: String,
    var coordinates: Coordinates,
    var creationDate: LocalDate = Utils.generateCreationDate(),
    var salary: Long,
    var startDate: ZonedDateTime = Utils.generateStartDate(),
    var endDate: LocalDate?,
    var position: Position?,
    var person: Person
) : Comparable<Worker> {
    override fun compareTo(other: Worker): Int {
        return id.compareTo(other.id)
    }

    override fun toString(): String {
        return "Worker(id=$id, name='$name', coordinates=$coordinates, creationDate=$creationDate," +
                "salary=$salary, startDate=$startDate, endDate=$endDate, position=$position, person=$person)"
    }
}