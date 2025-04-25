import Commands.*
import WorkerClass.*
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    // Путь к файлу по умолчанию
    val defaultFile = "workers.xml"

    // Загрузка данных из XML при старте
    if (args.isNotEmpty()) {
        XmlLoader.loadFromXml(args[0])
    } else {
        try {
            when {
                // Если файл существует - загружаем
                File(defaultFile).exists() -> {
                    XmlLoader.loadFromXml(defaultFile)
                    IOManager.printMessage("Коллекция загружена из $defaultFile (${WorkerManager.collection.size} работников)")
                }
                // Если файла нет - создаем пустую коллекцию
                else -> {
                    IOManager.printMessage("Файл $defaultFile не найден. Коллекция будет пустой.")
                }
            }
        } catch (e: Exception) {
            IOManager.printError("Ошибка при загрузке данных: ${e.message}")
            IOManager.printMessage("Коллекция будет пустой.")
        }
    }

    val scanner = Scanner(System.`in`)

    IOManager.printMessage("Программа для управления коллекцией работников. Введите 'help' для вывода доступных команд.")

    while (!Exit.shouldExit()) {
        print("> ")
        val userInput = scanner.nextLine().trim()
        if (userInput.isEmpty()) continue

        val inputParts = userInput.split(" ")
        val command = inputParts[0]
        val args = inputParts.drop(1)

        try {
            when (command) {
                "help" -> Help.helpCommand()
                "add" -> Add.addCommand()
                "show" -> Show.showCommand()
                "info" -> Info.infoCommand()
                "head" -> Head.headCommand()
                "remove_head" -> RemoveHead.remove_headCommand()
                "clear" -> Clear.clearCommand()
                "update" -> {
                    if (args.isEmpty()) {
                        IOManager.printError("Ошибка: отсутствует ID")
                    } else {
                        try {
                            Update.updateCommand(args[0].toInt())
                        } catch (e: NumberFormatException) {
                            IOManager.printError("Ошибка: ID должен быть числом")
                        }
                    }
                }
                "remove_by_id" -> {
                    if (args.isEmpty()) {
                        IOManager.printError("Ошибка: отсутствует ID")
                    } else {
                        try {
                            Remove.remove_by_id(args[0].toInt())
                        } catch (e: NumberFormatException) {
                            IOManager.printError("Ошибка: ID должен быть числом")
                        }
                    }
                }
                "save" -> Save.saveCommand(args.firstOrNull())
                "remove_greater" -> {
                    if (args.isEmpty()) {
                        IOManager.printError("Ошибка: отсутствует ID")
                    } else {
                        try {
                            RemoveGreater.remove_greaterCommand(args[0].toInt())
                        } catch (e: NumberFormatException) {
                            IOManager.printError("Ошибка: ID должен быть числом")
                        }
                    }
                }
                "count_less_than_position" -> {
                    if (args.isEmpty()) {
                        IOManager.printError("Ошибка: требуется указать позицию")
                    } else {
                        CountLessThanPosition.countLess_positionCommand(args[0])
                    }
                }
                "filter_starts_with_name" -> {
                    if (args.isEmpty()) {
                        IOManager.printError("Ошибка: требуется указать подстроку")
                    } else {
                        FilterStartsWithName.filterStarts_nameCommand(args[0])
                    }
                }
                "filter_less_than_position" -> {
                    if (args.isEmpty()) {
                        IOManager.printError("Ошибка: требуется указать позицию")
                    } else {
                        FilterLessThanPosition.filterLess_positionCommand(args[0])
                    }
                }
                "execute_script" -> {
                    if (args.isEmpty()) {
                        IOManager.printError("Ошибка: требуется указать файл скрипта")
                    } else {
                        Execute.executeCommand(args[0])
                    }
                }
                "exit" -> Exit.exitCommand()
                else -> IOManager.printError("Неизвестная команда. Введите 'help' для списка команд.")
            }
        } catch (e: Exception) {
            IOManager.printError("Ошибка выполнения команды: ${e.message}")
        }
    }
}