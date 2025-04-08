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