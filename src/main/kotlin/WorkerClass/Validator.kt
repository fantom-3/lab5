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
        return id > 0
    }

    /**
     * Валидация имени работника.
     * Имя не может быть null или пустым.
     */
    fun validateName(name: String?): Boolean {
        return !name.isNullOrBlank()
    }

    /**
     * Валидация координат.
     * Координата x не может быть null и должна быть <= 42.
     * Координата y должна быть <= 431.
     */
    fun validateCoordinates(x: Long?, y: Double?): Boolean {
        return x != null && y != null && x <= 42 && y <= 431
    }

    /**
     * Валидация зарплаты.
     * Зарплата не может быть null и должна быть больше 0.
     */
    fun validateSalary(salary: Long?): Boolean {
        return salary != null && salary > 0
    }

    /**
     * Валидация даты начала работы.
     * Дата не может быть null.
     */
    fun validateStartDate(startDate: ZonedDateTime?): Boolean {
        return startDate != null
    }

    /**
     * Валидация даты окончания работы.
     * Дата может быть null (если работник ещё работает).
     */
    fun validateEndDate(endDate: LocalDate?): Boolean {
        return true // Дата может быть null
    }

    /**
     * Валидация должности.
     * Должность может быть null.
     */
    fun validatePosition(position: Position?): Boolean {
        return true // Должность может быть null
    }

    /**
     * Валидация личных данных работника.
     * Поля birthday, hairColor и nationality не могут быть null.
     */
    fun validatePerson(birthday: LocalDateTime?, hairColor: Color?, nationality: Country?): Boolean {
        return birthday != null && hairColor != null && nationality != null
    }

    /**
     * Валидация цвета глаз.
     * Цвет глаз может быть null.
     */
    fun validateEyeColor(eyeColor: Color?): Boolean {
        return true // Цвет глаз может быть null
    }

    /**
     * Валидация всех данных работника.
     */
    fun validateWorker(worker: Worker): Boolean {
        return validateId(worker.id) &&
                validateName(worker.name) &&
                validateCoordinates(worker.coordinates.x, worker.coordinates.y) &&
                validateSalary(worker.salary) &&
                validateStartDate(worker.startDate) &&
                validateEndDate(worker.endDate) &&
                validatePosition(worker.position) &&
                validatePerson(worker.person.birthday, worker.person.hairColor, worker.person.nationality)
    }
}