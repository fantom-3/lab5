package Commands

import WorkerClass.WorkerManager
import WorkerClass.WorkerManager.firstWorker


/**
 * Команда для вывода и удаления первого элемента коллекции.
 * Выводит первый элемент коллекции, а затем удаляет его.
 */
object RemoveHead {

    /**
     * Выполняет вывод и удаление первого элемента коллекции.
     * Если коллекция пуста, выводит соответствующее сообщение.
     */
    fun remove_headCommand() {
        when {
            WorkerManager.collection.isEmpty() -> println("Коллекция пуста.")
            else -> {
                val worker = WorkerManager.collection.first()
                println(firstWorker(worker))
                WorkerManager.collection.removeFirst()
                println("Элемент успешно удален.")
            }
        }
    }
}