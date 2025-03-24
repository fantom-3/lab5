package WorkerClass

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

object Validator {
    /**
     * Валидация ID работника.
     * ID должен быть больше 0.
     */
    fun validateId(id: Int): Boolean {
        if (id <= 0) {
            println("Предупреждение: ID должен быть больше 0.")
            return false
        }
        return true
    }

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

    /**
     * Валидация даты окончания работы.
     * Дата может быть null (если работник ещё работает).
     */
    fun validateEndDate(endDate: LocalDate?): Boolean {
        // Дата окончания может быть null, поэтому всегда возвращаем true
        return true
    }

    /**
     * Валидация должности.
     * Должность может быть null.
     */
    fun validatePosition(position: Position?): Boolean {
        // Должность может быть null, поэтому всегда возвращаем true
        return true
    }

    /**
     * Валидация личных данных работника.
     * Поля birthday, hairColor и nationality не могут быть null.
     */
    fun validatePerson(birthday: LocalDateTime?, hairColor: Color?, nationality: Country?): Boolean {
        if (birthday == null) {
            println("Предупреждение: Дата рождения не может быть null.")
            return false
        }
        if (hairColor == null) {
            println("Предупреждение: Цвет волос не может быть null.")
            return false
        }
        if (nationality == null) {
            println("Предупреждение: Национальность не может быть null.")
            return false
        }
        return true
    }

    /**
     * Валидация цвета глаз.
     * Цвет глаз может быть null.
     */
    fun validateEyeColor(eyeColor: Color?): Boolean {
        // Цвет глаз может быть null, поэтому всегда возвращаем true
        return true
    }
}