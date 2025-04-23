import Commands.*
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    println("Программа для управления коллекцией работников. Введите 'help' для вывода доступных команд.")

    while (true) {
        print("> ")
        val userInput = scanner.nextLine().trim()
        if (userInput.isEmpty()) continue

        val inputParts = userInput.split(" ")
        val command = inputParts[0]
        val args = inputParts.drop(1) // Все части после команды

        when (command) {
            "help" -> Help.helpCommand()
            "add" -> Add.addCommand()
            "show" -> Show.showCommand()
            "info" -> Info.infoCommand()
            "head" -> Head.headCommand()
            "remove_head" -> RemoveHead.remove_headCommand()
            "clear" -> Clear.clearCommand()
            "update" -> {
                when {
                    args.isEmpty() -> println("Ошибка: отсутствует ID")
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> try {
                        Update.updateCommand(args[0].toInt())
                    } catch (e: NumberFormatException) {
                        println("Ошибка: ID должен быть числом")
                    }
                }
            }
            "remove_by_id" -> {
                when {
                    args.isEmpty() -> println("Ошибка: отсутствует ID")
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> try {
                        Remove.remove_by_id(args[0].toInt())
                    } catch (e: NumberFormatException) {
                        println("Ошибка: ID должен быть числом")
                    }
                }
            }
            "save" -> {
                when {
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> Save.saveCommand(args.firstOrNull())
                }
            }
            "remove_greater" -> {
                when {
                    args.isEmpty() -> println("Ошибка: отсутствует ID")
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> try {
                        RemoveGreater.remove_greaterCommand(args[0].toInt())
                    } catch (e: NumberFormatException) {
                        println("Ошибка: ID должен быть числом")
                    }
                }
            }
            "count_less_than_position" -> {
                when {
                    args.size == 0 -> println("Ошибка: введите аргумент")
                    else -> CountLessThanPosition.countLess_positionCommand(args[0])
                }
            }
            "filter_starts_with_name" -> {
                when {
                    args.isEmpty() -> println("Ошибка: требуется указать подстроку")
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> FilterStartsWithName.filterStarts_nameCommand(args[0])
                }
            }
            "filter_less_than_position" -> {
                when {
                    args.isEmpty() -> println("Ошибка: требуется указать позицию для фильтрации")
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> FilterLessThanPosition.filterLess_positionCommand(args[0])
                }
            }
            "execute_script" -> {
                when {
                    args.isEmpty() -> println("Ошибка: требуется указать файл скрипта")
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> Execute.executeCommand(args[0])
                }
            }
            "exit" -> Exit.exitCommand()
            else -> println("Неизвестная команда. Введите 'help' для списка команд.")
        }
    }
}