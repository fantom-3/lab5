import WorkerClass.*
import Commands.*
import java.util.*
import kotlin.system.exitProcess


fun main() {
    val scanner = Scanner(System.`in`)
    println("Программа для управления коллекцией работников. Введите 'help' для вывода доступных команд.")

    while (true) {
        print("> ")
        val input = scanner.nextLine().trim()
        when (input) {
            "help" -> Help.helpCommand()
            "exit" -> break
            else -> println("Неизвестная команда. Введите 'help' для списка команд.")
        }
    }
    // Добавление нового работника через консоль
    val worker = WorkerManager.newWorker(null)
    WorkerManager.collection["worker1"] = worker



    /*
    // Автоматическое создание работника
    val tokens = listOf(
        "John Doe", "10", "20.5", "50000", "2023-10-15T10:15:30+01:00[Europe/Paris]",
        "null", "MANAGER", "2000-01-01T00:00", "null", "BLACK", "GERMANY"
    )
    val autoWorker = WorkerManager.autoNewWorker(null, tokens)
    WorkerManager.collection["worker2"] = autoWorker

    // Поиск работника по id
    val foundWorker = WorkerManager.collection[WorkerManager.getKeyById(1)]
    println("Найден работник: $foundWorker")

    // Сортировка по убыванию зарплаты
    val sortedWorkers = WorkerManager.getDescendingWorkers()
    println("Работники, отсортированные по зарплате:")
    sortedWorkers.forEach { println(it) }
    */


}