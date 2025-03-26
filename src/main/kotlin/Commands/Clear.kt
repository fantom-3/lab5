package Commands
import  WorkerClass.WorkerManager

/**
 * Команда для очистки коллекции.
 * Удаляет все элементы из коллекции работников.
 */
object Clear {

    /**
     * Команда для очистки коллекции.
     * Удаляет все элементы из коллекции работников.
     */
    fun clearCommand() {
        println("Вы уверены, что хотите очистить коллекцию? (y/n)")
        print(">")

        when (readln().trim().lowercase()) {
            "y", "yes" -> {
                WorkerManager.clearCollection()
                println("Коллекция успешно очищена.")
            }

            else -> println("Очистка отменена.")
        }
    }
}