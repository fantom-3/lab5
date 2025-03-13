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
    val person: Position?,
    var position: Person
) : Comparable<Worker> {
    override fun compareTo(other: Worker): Int {
        return id.compareTo(other.id)
    }


    /*
    companion object {
        private var nextId = 1
    }

    init {
        this.id = nextId++
        this.creationDate = LocalDate.now()

        require(id > 0) {"поле id больше нуля"}
        require(salary >0) {"поле salary больше нуля"}
        require(name.isNotEmpty()) {"поле name не пустое"}
    }
    */

}