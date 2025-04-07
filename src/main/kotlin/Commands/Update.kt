package Commands

import WorkerClass.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

/**
 * Команда для обновления элемента коллекции по ID.
 */
object Update {

    /**
     * Выполняет обновление элемента коллекции по указанному ID.
     *
     * @param id ID элемента для обновления
     */
    fun updateCommand(id: Int) {
        val worker = WorkerManager.collection.find { it.id == id }

        if (worker == null) {
            println("Работник с ID $id не найден.")
            return
        }

        println("Обновление работника с ID $id. Оставьте поле пустым, чтобы сохранить текущее значение.")

        // Обновление имени
        worker.name = updateField(
            currentValue = worker.name,
            prompt = "Текущее имя: ${worker.name}. Введите новое имя:",
            validator = Validator::validateName
        ) ?: worker.name

        // Обновление координат
        worker.coordinates = updateCoordinates(worker.coordinates) ?: worker.coordinates

        // Обновление зарплаты
        worker.salary = updateField(
            currentValue = worker.salary,
            prompt = "Текущая зарплата: ${worker.salary}. Введите новую зарплату:",
            validator = Validator::validateSalary,
            parser = String::toLong
        ) ?: worker.salary

        // Обновление даты начала работы
        worker.startDate = updateField(
            currentValue = worker.startDate,
            prompt = "Текущая дата начала: ${worker.startDate}. Введите новую дату (ISO-8601):",
            validator = Validator::validateStartDate,
            parser = ZonedDateTime::parse
        ) ?: worker.startDate

        // Обновление даты окончания
        worker.endDate = updateField(
            currentValue = worker.endDate,
            prompt = "Текущая дата окончания: ${worker.endDate ?: "не указана"}. Введите новую дату (ISO-8601) или оставьте пустым:",
            validator = { true },
            parser = { if (it.isEmpty()) null else LocalDate.parse(it) }
        ) ?: worker.endDate

        // Обновление должности
        worker.position = updateEnumField(
            currentValue = worker.position,
            enumValues = Position.values(),
            prompt = "Текущая должность: ${worker.position ?: "не указана"}. Введите новую должность (${Position.values().joinToString()}):"
        ) ?: worker.position

        // Обновление личных данных
        worker.person = updatePerson(worker.person) ?: worker.person

        println("Работник с ID $id успешно обновлен.")
    }

    private fun <T> updateField(
        currentValue: T,
        prompt: String,
        validator: (T?) -> Boolean,
        parser: (String) -> T = { it as T }
    ): T? {
        println(prompt)
        while (true) {
            print("> ")
            val input = readln().trim()
            if (input.isEmpty()) return null

            try {
                val parsedValue = parser(input)
                if (validator(parsedValue)) {
                    return parsedValue
                }
            } catch (e: Exception) {
                println("Ошибка ввода. Повторите попытку.")
            }
        }
    }

    private fun updateCoordinates(current: Coordinates): Coordinates? {
        println("Текущие координаты: x=${current.x}, y=${current.y}. Введите новые координаты (x y):")
        while (true) {
            print("> ")
            val input = readln().trim()
            if (input.isEmpty()) return null

            try {
                val (x, y) = input.split(" ").map { it.trim() }
                val xVal = x.toLong()
                val yVal = y.toDouble()

                if (Validator.validateCoordinates(xVal, yVal)) {
                    return Coordinates(xVal, yVal)
                }
            } catch (e: Exception) {
                println("Ошибка: введите два числа через пробел (x y).")
            }
        }
    }

    private fun <T : Enum<T>> updateEnumField(
        currentValue: T?,
        enumValues: Array<T>,
        prompt: String
    ): T? {
        println(prompt)
        while (true) {
            print("> ")
            val input = readln().trim()
            if (input.isEmpty()) return null

            try {
                return enumValues.first { it.name.equals(input, ignoreCase = true) }
            } catch (e: NoSuchElementException) {
                println("Ошибка: введите одно из значений: ${enumValues.joinToString()}")
            }
        }
    }

    private fun updatePerson(current: Person): Person? {
        println("Обновление личных данных:")

        val birthday = updateField(
            currentValue = current.birthday,
            prompt = "Текущая дата рождения: ${current.birthday}. Введите новую дату (ISO-8601):",
            validator = { it != null },
            parser = LocalDateTime::parse
        ) ?: current.birthday

        val eyeColor = updateEnumField(
            currentValue = current.eyeColor,
            enumValues = Color.values(),
            prompt = "Текущий цвет глаз: ${current.eyeColor ?: "не указан"}. Введите новый цвет (${Color.values().joinToString()}):"
        )

        val hairColor = updateEnumField(
            currentValue = current.hairColor,
            enumValues = Color.values(),
            prompt = "Текущий цвет волос: ${current.hairColor}. Введите новый цвет (${Color.values().joinToString()}):"
        ) ?: current.hairColor

        val nationality = updateEnumField(
            currentValue = current.nationality,
            enumValues = Country.values(),
            prompt = "Текущая национальность: ${current.nationality}. Введите новую национальность (${Country.values().joinToString()}):"
        ) ?: current.nationality

        return Person(birthday, eyeColor, hairColor, nationality)
    }
}