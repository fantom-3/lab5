package Commands

import WorkerClass.WorkerManager

/**
 * Команда для удаления элемента коллекции по указанному ID.
 */
object Remove {

    /**
     * Выполняет удаление элемента коллекции по указанному ID.
     *
     * @param id ID элемента для удаления
     */
    fun remove_by_id(id: Int) {
        val initialSize = WorkerManager.collection.size

        // Используем removeIf для безопасного удаления
        WorkerManager.collection.removeIf { it.id == id }

        if (WorkerManager.collection.size < initialSize) {
            println("Элемент с ID $id успешно удален.")
        } else {
            println("Элемент с ID $id не найден.")
        }
    }
}