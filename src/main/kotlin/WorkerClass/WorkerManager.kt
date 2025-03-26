package WorkerClass

import java.util.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

/**
 * Объект управляет коллекцией работников.
 * Хранит данные в виде LinkedList.
 */
object WorkerManager {
    var collection = LinkedList<Worker>()

    /**
     * Возвращает максимальный id, которые используются.
     *
     * @return id, число.
     */
    fun getMaxId(): Int {
        return collection.maxOfOrNull { it.id } ?: 0
    }

    /**
     * Создает новый элемент коллекции, запрашивая поля для ввода через консоль.
     *
     * @param id id работника, если нужен. Иначе генерируется автоматически.
     *
     * @return Элемент коллекции.
     */

    /**
     * Создает новый элемент коллекции, запрашивая поля для ввода через консоль.
     *
     * @property id Уникальный идентификатор работника. Должен быть больше 0.
     * @property name Имя работника. Не может быть null или пустым.
     * @property coordinates Координаты работника. Не могут быть null.
     * @property creationDate Дата создания записи. Не может быть null.
     * @property salary Зарплата работника. Не может быть null и должна быть больше 0.
     * @property startDate Дата начала работы. Не может быть null.
     * @property endDate Дата окончания работы. Может быть null.
     * @property position Должность работника. Может быть null.
     * @property person Личные данные работника. Не может быть null.
     *
     * @return Элемент коллекции.
     */
    fun newWorker(id: Int?): Worker {
        val newId = id ?: Utils.generateId()
        val name = readValidName()
        val (x, y) = readValidCoordinates()
        val coordinates = Coordinates(x, y)
        val creationDate = Utils.generateCreationDate()
        val salary = readValidSalary()
        val startDate = readValidStartDate()
        val endDate = readValidEndDate()
        val position = readValidPosition()
        val person = readValidPerson()
        return Worker(newId, name, coordinates, creationDate, salary, startDate, endDate, position, person)
    }

    private fun readValidName(): String {
        println("Введите имя работника")
        while (true) {
            print("> ")
            val name = readln().trim()
            if (Validator.validateName(name)) {
                return name
            }
        }
    }

    private fun readValidCoordinates(): Pair<Long, Double> {
        println("Введите координаты работника в формате x y")
        while (true) {
            print("> ")
            val input = readln().trim().split(" ")
            if (input.size == 2) {
                try {
                    val x = input[0].toLong()
                    val y = input[1].toDouble()
                    if (Validator.validateCoordinates(x, y)) {
                        return x to y
                    }
                } catch (e: NumberFormatException) {
                    println("Ошибка: Введите два числа (x и y).")
                }
            } else {
                println("Ошибка: Введите два числа через пробел.")
            }
        }
    }

    private fun readValidSalary(): Long {
        println("Введите зарплату работника")
        while (true) {
            print("> ")
            try {
                val salary = readln().trim().toLong()
                if (Validator.validateSalary(salary)) {
                    return salary
                }
            } catch (e: NumberFormatException) {
                println("Ошибка: Введите число.")
            }
        }
    }

    private fun readValidStartDate(): ZonedDateTime {
        println("Введите дату начала работы в формате ISO-8601 (например, 2023-10-15T10:15:30+01:00[Europe/Paris])")
        while (true) {
            print("> ")
            try {
                val startDate = ZonedDateTime.parse(readln().trim())
                if (Validator.validateStartDate(startDate)) {
                    return startDate
                }
            } catch (e: Exception) {
                println("Ошибка: Введите дату в формате ISO-8601.")
            }
        }
    }

    private fun readValidEndDate(): LocalDate? {
        println("Введите дату окончания работы в формате ISO-8601 (или оставьте пустым)")
        while (true) {
            print("> ")
            val input = readln().trim()
            if (input.isEmpty()) {
                return null
            }
            try {
                return LocalDate.parse(input)
            } catch (e: Exception) {
                println("Ошибка: Введите дату в формате ISO-8601 или оставьте пустым.")
            }
        }
    }

    private fun readValidPosition(): Position? {
        println("Введите должность работника из указанных: MANAGER, LABORER, HUMAN_RESOURCES, ENGINEER, COOK")
        while (true) {
            print("> ")
            val input = readln().trim()
            if (input.isEmpty()) {
                return null
            }
            try {
                return Position.valueOf(input)
            } catch (e: IllegalArgumentException) {
                println("Ошибка: Введите одну из допустимых должностей.")
            }
        }
    }

    private fun readValidPerson(): Person {
        println("Введите личные данные работника")
        while (true) {
            val birthday = readValidBirthday()
            val eyeColor = readValidEyeColor()
            val hairColor = readValidHairColor()
            val nationality = readValidNationality()

            if (Validator.validatePerson(birthday, hairColor, nationality)) {
                return Person(birthday, eyeColor, hairColor, nationality)
            }
        }
    }

    private fun readValidBirthday(): LocalDateTime {
        println("Введите дату рождения в формате ISO-8601 (например, 2023-10-15T10:15:30)")
        while (true) {
            print("> ")
            try {
                return LocalDateTime.parse(readln().trim())
            } catch (e: Exception) {
                println("Ошибка: Введите дату в формате ISO-8601.")
            }
        }
    }

    private fun readValidEyeColor(): Color? {
        println("Введите цвет глаз из указанных: GREEN, RED, BLACK, WHITE (или оставьте пустым)")
        while (true) {
            print("> ")
            val input = readln().trim()
            if (input.isEmpty()) {
                return null
            }
            try {
                return Color.valueOf(input)
            } catch (e: IllegalArgumentException) {
                println("Ошибка: Введите один из допустимых цветов.")
            }
        }
    }

    private fun readValidHairColor(): Color {
        println("Введите цвет волос из указанных: GREEN, RED, BLACK, WHITE")
        while (true) {
            print("> ")
            try {
                return Color.valueOf(readln().trim())
            } catch (e: IllegalArgumentException) {
                println("Ошибка: Введите один из допустимых цветов.")
            }
        }
    }

    private fun readValidNationality(): Country {
        println("Введите национальность из указанных: GERMANY, CHINA, INDIA, VATICAN")
        while (true) {
            print("> ")
            try {
                return Country.valueOf(readln().trim())
            } catch (e: IllegalArgumentException) {
                println("Ошибка: Введите одну из допустимых национальностей.")
            }
        }
    }

    /**
     * Добавляет работника в коллекцию.
     *
     * @param worker Работник для добавления.
     */
    fun addWorker(worker: Worker) {
        collection.add(worker)
    }

    /**
     * Удаляет работника из коллекции по id.
     *
     * @param id id работника для удаления.
     */
    fun removeWorkerById(id: Int) {
        val worker = collection.find { it.id == id }
        if (worker != null) {
            collection.remove(worker)
            println("Работник с id $id удален.")
        } else {
            println("Работник с id $id не найден.")
        }
    }


    /**
     * Выводит всех работников в коллекции.
     */
    fun showAllWorkers() {
        collection.forEach { worker ->
            println(
                """
                |ID: ${worker.id}
                |Имя: ${worker.name}
                |Координаты: (x=${worker.coordinates.x}, y=${worker.coordinates.y})
                |Дата создания: ${worker.creationDate}
                |Зарплата: ${worker.salary}
                |Дата начала работы: ${worker.startDate}
                |Дата окончания работы: ${worker.endDate ?: "не указана"}
                |Должность: ${worker.position ?: "не указана"}
                |Личные данные:
                |  Дата рождения: ${worker.person.birthday}
                |  Цвет глаз: ${worker.person.eyeColor ?: "не указан"}
                |  Цвет волос: ${worker.person.hairColor}
                |  Национальность: ${worker.person.nationality}
                |-------------------------------------
                """.trimMargin()
            )
        }
    }
}














/*
    init {
        loadFromFile()
    }

    /**
     * Загружает данные из файла в коллекцию.
     */
    private fun loadFromFile() {
        try {
            val file = File(filename)
            if (!file.exists()) {
                println("Файл не найден. Создана новая коллекция.")
                return
            }

            val scanner = Scanner(file)
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val worker = parseWorker(line)
                if (worker != null) {
                    workers.add(worker)
                }
            }
            scanner.close()
        } catch (e: Exception) {
            println("Ошибка при чтении файла: ${e.message}")
        }
    }

    /**
     * Парсит строку из файла в объект Worker.
     */
    private fun parseWorker(line: String): Worker? {
        // Пример простого парсинга (реализация зависит от формата XML)
        return try {
            val parts = line.split(",")
            Worker(
                id = parts[0].toInt(),
                name = parts[1],
                coordinates = Coordinates(parts[2].toLong(), parts[3].toDouble()),
                creationDate = LocalDate.parse(parts[4]),
                salary = parts[5].toLong(),
                startDate = ZonedDateTime.parse(parts[6]),
                endDate = if (parts[7] == "null") null else LocalDate.parse(parts[7]),
                position = if (parts[8] == "null") null else Position.valueOf(parts[8]),
                person = Person(
                    birthday = Utils.generateCreationDate().parse(parts[9]),
                    eyeColor = if (parts[10] == "null") null else Color.valueOf(parts[10]),
                    hairColor = Color.valueOf(parts[11]),
                    nationality = Country.valueOf(parts[12])
                )
            )
        } catch (e: Exception) {
            println("Ошибка при парсинге строки: $line")
            null
        }
    }

    /**
     * Сохраняет данные коллекции в файл.
     */
    fun saveToFile() {
        try {
            FileWriter(filename).use { writer ->
                for (worker in workers) {
                    writer.write(workerToString(worker) + "\n")
                }
            }
        } catch (e: Exception) {
            println("Ошибка при записи в файл: ${e.message}")
        }
    }

    /**
     * Преобразует объект Worker в строку для записи в файл.
     */
    private fun workerToString(worker: Worker): String {
        return listOf(
            worker.id,
            worker.name,
            worker.coordinates.x,
            worker.coordinates.y,
            worker.creationDate,
            worker.salary,
            worker.startDate,
            worker.endDate ?: "null",
            worker.position ?: "null",
            worker.person.birthday,
            worker.person.eyeColor ?: "null",
            worker.person.hairColor,
            worker.person.nationality
        ).joinToString(",")
    }

    /**
     * Добавляет нового рабочего в коллекцию.
     */
    fun addWorker(worker: Worker) {
        workers.add(worker)
    }

    /**
     * Удаляет рабочего из коллекции по ID.
     */
    fun removeWorker(id: Int) {
        workers.removeIf { it.id == id }
    }

    /**
     * Выводит список всех рабочих.
     */
    fun listWorkers() {
        if (workers.isEmpty()) {
            println("Коллекция пуста.")
        } else {
            workers.forEach { println(it) }
        }
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Необходимо указать имя файла в качестве аргумента командной строки.")
        return
    }

    val manager = WorkerManager(args[0])
    // Пример использования
    manager.listWorkers()
}
*/