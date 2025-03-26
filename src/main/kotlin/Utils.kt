import java.time.LocalDate
import java.time.ZonedDateTime

object Utils {

    private var nextId: Int = 1

        fun generateId(): Int {
        return nextId++
    }

    fun generateCreationDate(): LocalDate {
        return LocalDate.now()
    }

    fun generateStartDate(): ZonedDateTime {
        return ZonedDateTime.now()
    }
}