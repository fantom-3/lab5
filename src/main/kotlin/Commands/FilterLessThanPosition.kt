package Commands

import WorkerClass.Position
import WorkerClass.Worker
import WorkerClass.WorkerManager


/**
 * Команда для фильтрации элементов с position меньше заданной
 */
object FilterLessThanPosition {

    /**
     * Выполняет фильтрацию элементов по position
     * @param positionInp Строковое представление позиции для сравнения
     */
    fun filterLess_positionCommand(positionInp: String) {
        val targetPosition = try {
            Position.valueOf(positionInp.uppercase())
        } catch (e: IllegalArgumentException) {
            println("Ошибка: $positionInp - недопустимая позиция")
            println("Допустимые позиции: ${Position.values().joinToString()}")
            return
        }

        val filtredWorkers = WorkerManager.collection.filter { worker -> worker.position != null && worker.position!! < targetPosition }

        Results(filtredWorkers, targetPosition)
    }

    private fun Results(workers: List<Worker>, tergetPosition: Position) {
        when {
            workers.isEmpty() -> println("Не найден элементов с position меньше $tergetPosition")
            else -> {
                println("Элементы с position меньше $tergetPosition (${workers.size} шт.):")
                workers.forEach { worker ->
                    println("""
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
                    """.trimMargin())
                }
            }
        }
    }
}