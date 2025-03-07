package WorkerClass

import jdk.jfr.internal.test.DeprecatedMethods.counter
import java.time.LocalDate
import java.time.ZonedDateTime

class Worker (
    val id: Int = counter++,
    val name: String,
    val coordinates: Coordinates,
    val creationDate: LocalDate = LocalDate.now(),
    val salary: Long,
    val StartDate: ZonedDateTime,
    val Person: Person,
    var EndDate: LocalDate? = null,
    var Position: Position? = null
)
{}