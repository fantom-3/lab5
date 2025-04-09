package Commands

import kotlin.system.exitProcess

/**
 * Команда для завершения программы (без сохранения в файл)
 */
object Exit {

    /**
     * Завершает программу с подтверждением
     */
    fun exitCommand() {
        println("Вы уверены, что хотите выйти без сохранения? (y/n)")
        print("> ")

        when (readln().trim().lowercase()) {
            "y", "yes" -> {
                println("Завершение программы...")
                exitProcess(0)  // Немедленное завершение
            }
            else -> println("Выход отменен.")
        }
    }
}