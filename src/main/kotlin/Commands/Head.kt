package Commands

import WorkerClass.*
import WorkerClass.WorkerManager.firstWorker


/**
 * Команда для вывода первого элемента коллекции.
 * Выводит первый элемент коллекции в строковом представлении.
 */
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
}