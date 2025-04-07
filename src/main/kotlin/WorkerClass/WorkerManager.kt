package WorkerClass

import java.util.*

/**
 * Объект управляет коллекцией работников.
 * Хранит данные в виде LinkedList.
 */
object WorkerManager {
    var collection = LinkedList<Worker>()

    /**
     * Добавляет работника в коллекцию.
     *
     * @param worker Работник для добавления.
     */
    fun addWorker(worker: Worker) {
        collection.add(worker)
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

    fun firstWorker(worker: Worker): String {
        return """
            |Первый элемент коллекции:
            |ID: ${worker.id}
            |Имя: ${worker.name}
            |Координаты: (x=${worker.coordinates.x}, y=${worker.coordinates.y})
            |Дата создания: ${worker.creationDate}
            |Зарплата: ${worker.salary}
            |Дата начала работы: ${worker.startDate}
            |Дата окончания: ${worker.endDate ?: "не указана"}
            |Должность: ${worker.position ?: "не указана"}
            |Личные данные:
            |   Дата рождения: ${worker.person.birthday}
            |   Цвет глаз: ${worker.person.eyeColor ?: "не указан"}
            |   Цвет волос: ${worker.person.hairColor}
            |   Национальность: ${worker.person.nationality}
        """.trimIndent()
    }

    /**
     * Очищает коллекцию работников.
     */
    fun clearCollection() {
        collection.clear()
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