package WorkerClass

import java.util.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

/**
 * Объект управляет коллекцией работников.
 * Хранит данные в виде HashMap, где ключ — строка, а значение — объект Worker.
 */
object WorkerManager {
    var collection = HashMap<String, Worker>()

    /**
     * Возвращает максимальный id, которые используются.
     *
     * @return id, число.
     */
    fun getMaxId(): Int {
        return collection.values.maxOfOrNull { it.id } ?: 0
    }

    /**
     * Создает новый элемент коллекции, запрашивая поля для ввода через консоль.
     *
     * @param id id работника, если нужен. Иначе генерируется автоматически.
     *
     * @return Элемент коллекции.
     */
    /*fun newWorker(id: Int?): Worker {
        val newId = Utils.generateId()

        println("Введите имя работника")
        print("> ")
        val name = readln().takeIf { it.isNotBlank() } ?: throw IllegalArgumentException("Имя не может быть пустым")

        println("Введите координаты работника в формате x y")
        print("> ")
        val (x, y) = readCoordinates()

        val coordinates = Coordinates(x, y)

        val creationDate = LocalDate.now()

        println("Введите зарплату работника")
        print("> ")
        val salary = readSalary()

        println("Введите дату начала работы в формате ISO-8601 (например, 2023-10-15T10:15:30+01:00[Europe/Paris])")
        print("> ")
        val startDate = readZonedDateTime()

        println("Введите дату окончания работы в формате ISO-8601 (или оставьте пустым)")
        print("> ")
        val endDate = readLocalDateOrNull()

        println("Введите должность работника из указанных: MANAGER, LABORER, HUMAN_RESOURCES, ENGINEER, COOK")
        print("> ")
        val position = readPosition()

        print("Введите личные данные работника")
        val person = readPerson()

        return Worker(newId, name, coordinates, creationDate, salary, startDate, endDate, position, person)
    }*/

    fun newWorker(id: Int?): Worker {
        val newId = id ?: Utils.generateId()

        println("Введите имя работника")
        print("> ")
        val name = readln().takeIf { Validator.validateName(it) }
            ?: throw IllegalArgumentException("Имя не может быть пустым")

        println("Введите координаты работника в формате x y")
        print("> ")
        val (x, y) = readCoordinates()
        if (!Validator.validateCoordinates(x, y)) {
            throw IllegalArgumentException("Некорректные координаты")
        }
        val coordinates = Coordinates(x, y)

        val creationDate = LocalDate.now()

        println("Введите зарплату работника")
        print("> ")
        val salary = readSalary()
        if (!Validator.validateSalary(salary)) {
            throw IllegalArgumentException("Зарплата должна быть больше 0")
        }

        println("Введите дату начала работы в формате ISO-8601 (например, 2023-10-15T10:15:30+01:00[Europe/Paris])")
        print("> ")
        val startDate = readZonedDateTime()
        if (!Validator.validateStartDate(startDate)) {
            throw IllegalArgumentException("Некорректная дата начала работы")
        }

        println("Введите дату окончания работы в формате ISO-8601 (или оставьте пустым)")
        print("> ")
        val endDate = readLocalDateOrNull()
        if (!Validator.validateEndDate(endDate)) {
            throw IllegalArgumentException("Некорректная дата окончания работы")
        }

        println("Введите должность работника из указанных: MANAGER, LABORER, HUMAN_RESOURCES, ENGINEER, COOK")
        print("> ")
        val position = readPosition()
        if (!Validator.validatePosition(position)) {
            throw IllegalArgumentException("Некорректная должность")
        }

        println("Введите личные данные работника")
        val person = readPerson()
        if (!Validator.validatePerson(person.birthday, person.hairColor, person.nationality)) {
            throw IllegalArgumentException("Некорректные личные данные")
        }

        return Worker(newId, name, coordinates, creationDate, salary, startDate, endDate, position, person)
    }

    /**
     * Создает новый элемент коллекции, получая поля через аргумент (для автоматического выполнения).
     *
     * @param id id работника, если нужен. Иначе генерируется автоматически.
     * @param tokens Список из аргументов, из которых формируются поля.
     *
     * @return Элемент коллекции.
     */
    fun autoNewWorker(id: Int?, tokens: List<String>): Worker {
        val newId = id ?: Utils.generateId()

        val name = tokens[0]

        val (x, y) = tokens[1].toLong() to tokens[2].toDouble()
        val coordinates = Coordinates(x, y)

        val creationDate = LocalDate.now()

        val salary = tokens[3].toLong()

        val startDate = ZonedDateTime.parse(tokens[4])

        val endDate = tokens[5].takeIf { it != "null" }?.let { LocalDate.parse(it) }

        val position = tokens[6].takeIf { it != "null" }?.let { Position.valueOf(it) }

        val person = Person(
            birthday = LocalDateTime.parse(tokens[7]),
            eyeColor = tokens[8].takeIf { it != "null" }?.let { Color.valueOf(it) },
            hairColor = Color.valueOf(tokens[9]),
            nationality = Country.valueOf(tokens[10])
        )

        return Worker(newId, name, coordinates, creationDate, salary, startDate, endDate, position, person)
    }

    /**
     * Выдает значение ключа по id.
     *
     * @param id id элемента.
     *
     * @return Ключ элемента.
     */
    fun getKeyById(id: Int): String {
        return collection.entries.find { it.value.id == id }?.key ?: ""
    }

    /**
     * Выдает значение ключа по зарплате.
     *
     * @param salary зарплата элемента.
     *
     * @return Ключ элемента.
     */
    fun getKeyBySalary(salary: Long): String {
        return collection.entries.find { it.value.salary == salary }?.key ?: ""
    }

    /**
     * Выдает значение ключа по имени, берет минимальное.
     *
     * @return Ключ элемента.
     */
    fun getMinByNameKey(): String {
        return collection.entries.minByOrNull { it.value.name }?.key ?: ""
    }

    /**
     * Выдает список работников, отсортированный по убыванию зарплаты.
     *
     * @return Список работников.
     */
    fun getDescendingWorkers(): List<Worker> {
        return collection.values.sortedByDescending { it.salary }
    }

    /**
     * Чтение координат из консоли.
     *
     * @return Пара (x, y).
     */
    private fun readCoordinates(): Pair<Long, Double> {
        while (true) {
            try {
                val (x, y) = readln().split(" ").map { it.trim() }
                val xValue = x.toLong()
                val yValue = y.toDouble()
                return xValue to yValue
                /*if (yValue <= 431 || xValue <= 42) {
                    return xValue to yValue
                } else {
                    println("Ошибка: не корректно введены координаты")
                }*/
            } catch (e: Exception) {
                println("Ошибка при вводе координат. Введите два числа через пробел (x y).")
                print(">")
            }
        }
    }

    /**
     * Чтение зарплаты из консоли.
     *
     * @return Зарплата.
     */
    private fun readSalary(): Long {
        while (true) {
            try {
                val salary = readln().toLong()
                if (salary > 0) {
                    return salary
                } else {
                    println("Ошибка: зарплата должна быть больше 0")
                }
            } catch (e: Exception) {
                println("Ошибка при вводе зарплаты. Введите число.")
            }
        }
    }

    /**
     * Чтение даты и времени из консоли.
     *
     * @return ZonedDateTime.
     */
    private fun readZonedDateTime(): ZonedDateTime {
        while (true) {
            try {
                return ZonedDateTime.parse(readln())
            } catch (e: Exception) {
                println("Ошибка при вводе даты. Введите дату в формате ISO-8601.")
            }
        }
    }

    /**
     * Чтение даты из консоли или null.
     *
     * @return LocalDate или null.
     */
    private fun readLocalDateOrNull(): LocalDate? {
        while (true) {
            val input = readln()
            if (input.isBlank()) return null
            try {
                return LocalDate.parse(input)
            } catch (e: Exception) {
                println("Ошибка при вводе даты. Введите дату в формате ISO-8601 или оставьте пустым.")
            }
        }
    }

    /**
     * Чтение должности из консоли.
     *
     * @return Position или null.
     */
    private fun readPosition(): Position? {
        while (true) {
            try {
                val input = readln()
                if (input.isBlank()) return null
                return Position.valueOf(input)
            } catch (e: Exception) {
                println("Ошибка при вводе должности. Введите одно из: MANAGER, LABORER, HUMAN_RESOURCES, ENGINEER, COOK.")
            }
        }
    }

    /**
     * Чтение личных данных работника из консоли.
     *
     * @return Объект Person.
     */
    private fun readPerson(): Person {
        println("Введите дату рождения в формате ISO-8601 (например, 2023-10-15T10:15:30)")
        print("> ")
        val birthday = readLocalDateTime()

        println("Введите цвет глаз из указанных: GREEN, RED, BLACK, WHITE (или оставьте пустым)")
        print("> ")
        val eyeColor = readColorOrNull()

        println("Введите цвет волос из указанных: GREEN, RED, BLACK, WHITE")
        print("> ")
        val hairColor = readColor()

        println("Введите национальность из указанных: GERMANY, CHINA, INDIA, VATICAN")
        print("> ")
        val nationality = readCountry()

        return Person(birthday, eyeColor, hairColor, nationality)
    }

    /**
     * Чтение даты и времени из консоли.
     *
     * @return LocalDateTime.
     */
    private fun readLocalDateTime(): LocalDateTime {
        while (true) {
            try {
                return LocalDateTime.parse(readln())
            } catch (e: Exception) {
                println("Ошибка при вводе даты. Введите дату в формате ISO-8601.")
            }
        }
    }

    /**
     * Чтение цвета из консоли или null.
     *
     * @return Color или null.
     */
    private fun readColorOrNull(): Color? {
        while (true) {
            val input = readln()
            if (input.isBlank()) return null
            try {
                return Color.valueOf(input)
            } catch (e: Exception) {
                println("Ошибка при вводе цвета. Введите одно из: GREEN, RED, BLACK, WHITE.")
            }
        }
    }

    /**
     * Чтение цвета из консоли.
     *
     * @return Color.
     */
    private fun readColor(): Color {
        while (true) {
            try {
                return Color.valueOf(readln())
            } catch (e: Exception) {
                println("Ошибка при вводе цвета. Введите одно из: GREEN, RED, BLACK, WHITE.")
            }
        }
    }

    /**
     * Чтение национальности из консоли.
     *
     * @return Country.
     */
    private fun readCountry(): Country {
        while (true) {
            try {
                return Country.valueOf(readln())
            } catch (e: Exception) {
                println("Ошибка при вводе национальности. Введите одно из: GERMANY, CHINA, INDIA, VATICAN.")
            }
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