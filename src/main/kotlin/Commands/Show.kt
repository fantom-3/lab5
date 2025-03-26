package Commands

import WorkerClass.WorkerManager

/**
 * Команда для вывода всех элементов коллекции в строковом представлении.
 */
object Show {

    /**
     * Выполняет команду вывода всех элементов коллекции.
     * Элементы выводятся в стандартный поток вывода.
     */
    fun showCommand() {
        if (WorkerManager.collection.isEmpty()) {
            println("Коллекция пуста.")
        } else {
            println("Элементы коллекции.")
            WorkerManager.showAllWorkers()
        }
    }
}