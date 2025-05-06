package Commands

import WorkerClass.IOManager
import java.io.File
import java.util.Stack

object Execute {
    private val executedScripts = Stack<String>()

    /**
     * Выполняет скрипт из указанного файла
     * @param filename путь к файлу скрипта
     */
    fun executeCommand(filename: String) {
        // Проверка рекурсии
        if (executedScripts.contains(filename)) {
            IOManager.printError("Обнаружена рекурсия в скрипте $filename")
            return
        }

        val file = File(filename)
        when {
            !file.exists() -> {
                IOManager.printError("Файл скрипта $filename не найден")
                return
            }
            !file.canRead() -> {
                IOManager.printError("Нет прав на чтение файла $filename")
                return
            }
            file.length() == 0L -> {
                IOManager.printError("Файл скрипта $filename пуст")
                return
            }
        }

        executedScripts.push(filename)
        IOManager.printMessage("Выполнение скрипта $filename...")

        try {
            file.forEachLine { line ->
                if (line.isNotBlank() && !line.trimStart().startsWith("//")) {
                    processCommandLine(line.trim())
                }
            }
            IOManager.printMessage("Скрипт $filename успешно выполнен")
        } catch (e: Exception) {
            IOManager.printError("Ошибка выполнения скрипта: ${e.message}")
        } finally {
            executedScripts.pop()
        }
    }

    private fun processCommandLine(line: String) {
        val parts = line.split("\\s+".toRegex())
        val command = parts[0]
        val args = parts.drop(1)

        when (command) {
            "execute_script" -> {
                if (args.isNotEmpty()) {
                    executeCommand(args[0])
                } else {
                    IOManager.printError("Не указан файл скрипта")
                }
            }
            else -> {
                // Эмулируем обычный ввод команды
                IOManager.printMessage("> $line")
                CommandRegistry.executeCommand(line)
            }
        }
    }
}