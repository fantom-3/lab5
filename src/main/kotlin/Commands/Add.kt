package Commands

import Utils
import WorkerClass.*
import java.time.LocalDateTime
import java.time.ZonedDateTime

object Add {
    fun addCommand() {
        IOManager.printMessage("Добавление нового работника:")

        val worker = Worker(
            id = Utils.generateId(),
            name = readValidName(),
            coordinates = readValidCoordinates(),
            creationDate = Utils.generateCreationDate(),
            salary = readValidSalary(),
            startDate = readValidStartDate(),
            endDate = readValidEndDate(),
            position = readValidPosition(),
            person = readValidPerson()
        )
        WorkerManager.addWorker(worker)
        IOManager.printMessage("Работник c ID - ${worker.id} успешно добавлен в коллекцию.")
    }

    private fun readValidName(): String {
        return IOManager.readString(
            fieldName = "имя работника",
            validator = { it.matches(Regex("^[\\p{L} ]+$")) && it.isNotEmpty() },
            isRequired = true
        )!!
    }

    private fun readValidCoordinates(): Coordinates {
        IOManager.printMessage("Ввод координат:")
        val x = IOManager.readLong(
            fieldName = "координата x (целое число ≤ 42)",
            validator = { it <= 42 },
            isRequired = true
        )!!.toFloat()

        val y = IOManager.readDouble(
            fieldName = "координата y (число ≤ 431)",
            validator = { it <= 431 },
            isRequired = true
        )!!.toLong()

        return Coordinates(x, y)
    }

    private fun readValidSalary(): Long {
        return IOManager.readLong(
            fieldName = "зарплата (целое число > 0)",
            validator = { it > 0 },
            isRequired = true
        )!!
    }

    private fun readValidStartDate(): ZonedDateTime {
        return IOManager.readZonedDateTime(
            fieldName = "дату начала работы (ГГГГ-ММ-ДДTЧЧ:ММ:СС+Зона)",
            isRequired = true
        )!!
    }

    private fun readValidEndDate(): LocalDateTime? {
        return IOManager.readLocalDateTime(
            fieldName = "дату окончания работы (ГГГГ-ММ-ДДTЧЧ:ММ:СС)",
            isRequired = false
        )
    }

    private fun readValidPosition(): Position? {
        return IOManager.readEnum(
            fieldName = "должность",
            enumValues = Position.values(),
            isRequired = false
        )
    }

    private fun readValidPerson(): Person {
        IOManager.printMessage("Ввод личных данных работника:")
        val birthday = IOManager.readLocalDateTime(
            fieldName = "дата рождения (ГГГГ-ММ-ДДTЧЧ:ММ:СС)",
            isRequired = true
        )!!

        val eyeColor = IOManager.readEnum(
            fieldName = "цвет глаз",
            enumValues = Color.values(),
            isRequired = false
        )

        val hairColor = IOManager.readEnum(
            fieldName = "цвет волос",
            enumValues = Color.values(),
            isRequired = true
        )!!

        val nationality = IOManager.readEnum(
            fieldName = "национальность",
            enumValues = Country.values(),
            isRequired = true
        )!!

        return Person(birthday, eyeColor, hairColor, nationality)
    }
}