package Commands

import WorkerClass.*
import java.util.*


object Head {

    /**
     * Выполняет вывод первого элемента коллекции.
     * Если коллекция пуста, выводит соответствующее сообщение.
     */
    fun headCommand() {
        when {
            WorkerManager.collection.isEmpty() -> println("Коллекция пуста.")
            else -> println(firstWorker(WorkerManager.collection.first()))
        }
    }

    private fun firstWorker(worker: Worker): String {
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
}