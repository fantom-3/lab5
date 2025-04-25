import Commands.CommandRegistry
import WorkerClass.IOManager
import WorkerClass.XmlLoader
import java.io.File
import java.util.*

fun main() {
    // Загрузка данных
    val defaultFile = "workers.xml"
    if (File(defaultFile).exists()) {
        XmlLoader.loadFromXml(defaultFile)
    }

    // Основной цикл
    val scanner = Scanner(System.`in`)
    IOManager.printMessage("Программа готова к работе. Введите 'help' для списка команд.")

    while (!Commands.Exit.shouldExit()) {
        print("> ")
        val input = scanner.nextLine().trim()
        if (input.isEmpty()) continue

        CommandRegistry.executeCommand(input)
    }
}