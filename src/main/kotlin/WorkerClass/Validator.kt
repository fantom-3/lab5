package WorkerClass

import java.time.ZonedDateTime

object Validator {
    /**
     * Валидация имени работника.
     * Имя не может быть null или пустым.
     */
    fun validateName(name: String?): Boolean {
        if (name.isNullOrBlank()) {
            println("Предупреждение: Имя не может быть пустым.")
            return false
        }
        return true
    }

    /**
     * Валидация координат.
     * Координата x не может быть null и должна быть <= 42.
     * Координата y должна быть <= 431.
     */
    fun validateCoordinates(x: Long?, y: Double?): Boolean {
        if (x == null || y == null) {
            println("Предупреждение: Координаты не могут быть null.")
            return false
        }
        if (x > 42 || y > 431) {
            println("Предупреждение: Координаты выходят за допустимые пределы (x <= 42, y <= 431).")
            return false
        }
        return true
    }

    /**
     * Валидация зарплаты.
     * Зарплата не может быть null и должна быть больше 0.
     */
    fun validateSalary(salary: Long?): Boolean {
        if (salary == null || salary <= 0) {
            println("Предупреждение: Зарплата должна быть больше 0.")
            return false
        }
        return true
    }

    /**
     * Валидация даты начала работы.
     * Дата не может быть null.
     */
    fun validateStartDate(startDate: ZonedDateTime?): Boolean {
        if (startDate == null) {
            println("Предупреждение: Дата начала работы не может быть null.")
            return false
        }
        return true
    }
}