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
    /*fun remove_by_id(id: Int) {
        val iterator = WorkerManager.collection.iterator()
        var found = false

        while (iterator.hasNext()) {
            val worker = iterator.next()
            if (worker.id == id) {
                iterator.remove()
                found = true
                break
            }
        }

        if (found) {
            println("Элемент с ID $id  успешно удален.")
        } else {
            println("Элемент с ID $id не найден в коллекции.")
        }
    }*/
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