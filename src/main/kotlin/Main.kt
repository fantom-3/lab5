import java.util.*
import Commands.*

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
            "clear" -> Clear.clearCommand()
            "save" -> {
                when {
                    args.size > 1 -> println("Ошибка: слишком много аргументов")
                    else -> SaveCommand.execute(args.firstOrNull())
                }
            }
            "exit" -> break
            else -> println("Неизвестная команда. Введите 'help' для списка команд.")
        }
    }
}