package Commands

import WorkerClass.WorkerManager

object RemoveGreater {

    fun remove_greaterCommand(id: Int) {
        val initialSize = WorkerManager.collection.size

        val iterator = WorkerManager.collection.iterator()
        var removedCount = 0

        while (iterator.hasNext()) {
            if (iterator.next().id > id) {
                iterator.remove()
                removedCount++
            }
        }

        when (removedCount) {
            0 -> println("Не найдено элементов с ID больше $id")
            else -> println("Удалено $removedCount элементов")
        }
    }
}