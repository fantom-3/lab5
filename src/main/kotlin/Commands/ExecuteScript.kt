package Commands

import java.io.File
import java.util.Scanner

object Execute {
    private val activeScripts = mutableSetOf<String>()

    fun executeCommand(filename: String) {
        val file = File(filename).canonicalFile

        if (!file.exists()) {
            println("Файл скрипта не найден: ${file.path}")
            return
        }

        if (file.path in activeScripts) {
            println("Ошибка: рекурсивный вызов скрипта ${file.name}")
            return
        }

        activeScripts.add(file.path)
        Scanner(file).use { scanner ->
            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine().trim())
            }
        }
        activeScripts.remove(file.path)
    }

    private fun processLine(line: String) {
        when {
            line.isBlank() || line.startsWith("//") -> return
            else -> CommandProcessor.process(line)
        }
    }
}