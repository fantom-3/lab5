package Commands

import WorkerClass.IOManager
import java.util.*

object CommandRegistry {
    private val commands = mutableMapOf<String, (List<String>) -> Unit>()

    init {
        // Регистрируем все команды
        register("help") { Help.helpCommand() }
        register("add") { Add.addCommand() }
        register("show") { Show.showCommand() }
        register("info") { Info.infoCommand() }
        register("head") { Head.headCommand() }
        register("remove_head") { RemoveHead.remove_headCommand() }
        register("clear") { Clear.clearCommand() }
        register("update") { args -> handleUpdate(args) }
        register("remove_by_id") { args -> handleRemoveById(args) }
        register("save") { args -> Save.saveCommand(args.firstOrNull()) }
        register("remove_greater") { args -> handleRemoveGreater(args) }
        register("count_less_than_position") { args -> handleCountLessThanPosition(args) }
        register("filter_starts_with_name") { args -> handleFilterStartsWithName(args) }
        register("filter_less_than_position") { args -> handleFilterLessThanPosition(args) }
        // register("execute_script") { args -> handleExecuteScript(args) }
        register("exit") { Exit.exitCommand() }
    }

    private fun register(name: String, handler: (List<String>) -> Unit) {
        commands[name] = handler
    }

    fun executeCommand(input: String) {
        val parts = input.trim().split("\\s+".toRegex())
        if (parts.isEmpty()) return

        val command = parts[0]
        val args = parts.drop(1)

        try {
            commands[command]?.invoke(args)
                ?: IOManager.printError("Неизвестная команда. Введите 'help' для списка команд.")
        } catch (e: Exception) {
            IOManager.printError("Ошибка выполнения команды: ${e.message}")
        }
    }

    // Обработчики сложных команд
    private fun handleUpdate(args: List<String>) {
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

    private fun handleRemoveById(args: List<String>) {
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

    private fun handleRemoveGreater(args: List<String>) {
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

    private fun handleCountLessThanPosition(args: List<String>) {
        if (args.isEmpty()) {
            IOManager.printError("Ошибка: требуется указать позицию")
        } else {
            CountLessThanPosition.countLess_positionCommand(args[0])
        }
    }

    private fun handleFilterStartsWithName(args: List<String>) {
        if (args.isEmpty()) {
            IOManager.printError("Ошибка: требуется указать подстроку")
        } else {
            FilterStartsWithName.filterStarts_nameCommand(args[0])
        }
    }

    private fun handleFilterLessThanPosition(args: List<String>) {
        if (args.isEmpty()) {
            IOManager.printError("Ошибка: требуется указать позицию")
        } else {
            FilterLessThanPosition.filterLess_positionCommand(args[0])
        }
    }

    /*private fun handleExecuteScript(args: List<String>) {
        if (args.isEmpty()) {
            IOManager.printError("Ошибка: требуется указать файл скрипта")
        } else {
            Execute.executeCommand(args[0])
        }
    }*/
}