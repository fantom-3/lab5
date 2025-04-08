package Commands

import WorkerClass.WorkerManager
import WorkerClass.Worker

/**
 * Команда для фильтрации элементов по началу имени
 */
object FilterStartsWithName {

    /**
     * Выполняется фильтрация и вывод элементов, чье имя начинается с заданной подстроки
     * @param prefix Подстрока для поиска в начале имени
     */
    fun filterStarts_nameCommand(prefix: String) {
        val filtredWorkers = WorkerManager.collection.filter { worker -> worker.name.startsWith(prefix) }
        when {
            filtredWorkers.isEmpty() -> println("Не найдено элементов с именем, начинающимся на $prefix")
            else -> {
                println("Найдено ${filtredWorkers.size} элементов:")
                filtredWorkers.forEach {worker -> println(format_posWorker(worker))}
            }
        }
    }

    private fun format_posWorker(worker: Worker): String {
        return """
                |ID: ${worker.id}
                |Имя: ${worker.name}
                |Должность: ${worker.position ?: "не указана"}
                |----------
            """.trimMargin()
    }
}