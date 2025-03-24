package WorkerClass

import Utils
import java.time.LocalDate
import java.time.ZonedDateTime

class Worker(
    var id: Int = Utils.generateId(),
    private val name: String,
    private val coordinates: Coordinates,
    private var creationDate: LocalDate = Utils.generateCreationDate(),
    private val salary: Long,
    private val startDate: ZonedDateTime = Utils.generateStartDate(),
    private var endDate: LocalDate?,
    private var position: Position?,
    private val person: Person
) : Comparable<Worker> {
    override fun compareTo(other: Worker): Int {
        return id.compareTo(other.id)
    }

    override fun toString(): String {
        return "Worker(id=$id, name='$name', coordinates=$coordinates, creationDate=$creationDate," +
                "salary=$salary, startDate=$startDate, endDate=$endDate, position=$position, person=$person)"
    }
}