package Commands

import WorkerClass.WorkerManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Команда для вывода информации о коллекции.
 * Выводит тип коллекции, дату инициализации, количество элементов и другую метаинформацию.
 */
object Info {

    // Хранит дату инициализации коллекции
    private val initializationDate: LocalDateTime = LocalDateTime.now()
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")


    /**
     * Выполняет команду вывода информации о коллекции.
     */
    fun infoCommand() {
        println("Информация о коллекции:")
        println("Тип коллекции: ${WorkerManager.collection.javaClass.simpleName}")
        println("Дата инициализации: ${initializationDate.format(dateFormatter)}")
        println("Количество элементов: ${WorkerManager.collection.size}")
        println("Тип хранимых элементов: ${WorkerManager.collection.firstOrNull()?.javaClass?.simpleName ?: "Не определено"}")
        println("Автоматически генерируемые поля: id, creationDate")

    }
}