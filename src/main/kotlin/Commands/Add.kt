package Commands

import WorkerClass.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

object Add {
    fun addCommand() {
        println("Добавление нового работника:")

        val worker = Worker(
            id = Utils.generateId(),
            name = readValidName(),
            coordinates = readValidCoordinates(),
            creationDate = Utils.generateCreationDate(),
            salary = readValidSalary(),
            startDate = readValidStartDate(),
            endDate = readValidEndDate(),
            position = readValidPosition(),
            person = readValidPerson()
        )
        WorkerManager.addWorker(worker)
        println("Работник c ID - ${worker.id} успешно добавлен в коллекцию.")
    }

        /**
         * Чтение и валидация имени работника.
         *
         * @return Валидное имя работника.
         */
        private fun readValidName(): String {
            println("Введите имя работника (только буквы и пробелы):")
            val nameRegex = Regex("^[\\p{L} ]+\$") // Только буквы и пробелы
            while (true) {
                print("> ")
                val name = readln().trim()
                when {
                    name.isEmpty() -> {
                        println("Ошибка: Имя не может быть пустым. Повторите ввод.")
                    }
                    !name.matches(nameRegex) -> {
                        println("Ошибка: Имя может содержать только буквы и пробелы. Повторите ввод.")
                    }
                    else -> return name
                }
            }
        }

        /**
         * Чтение и валидация координат.
         *
         * @return Валидные координаты.
         */
        private fun readValidCoordinates(): Coordinates {
            println("Введите координаты работника (x y):")
            while (true) {
                print("> ")
                val input = readln().trim().split(" ")
                if (input.size == 2) {
                    try {
                        val x = input[0].toLong()
                        val y = input[1].toDouble()
                        if (Validator.validateCoordinates(x, y)) {
                            return Coordinates(x, y)
                        }
                    } catch (e: NumberFormatException) {
                        println("Ошибка: Введите два числа (x и y).")
                    }
                } else {
                    println("Ошибка: Введите два числа через пробел.")
                }
            }
        }

        /**
         * Чтение и валидация зарплаты.
         *
         * @return Валидная зарплата.
         */
        private fun readValidSalary(): Long {
            println("Введите зарплату работника:")
            while (true) {
                print("> ")
                try {
                    val salary = readln().trim().toLong()
                    if (Validator.validateSalary(salary)) {
                        return salary
                    }
                } catch (e: NumberFormatException) {
                    println("Ошибка: Введите число.")
                }
            }
        }

        /**
         * Чтение и валидация даты начала работы.
         *
         * @return Валидная дата начала работы.
         */
        private fun readValidStartDate(): ZonedDateTime {
            println("Введите дату начала работы в формате ISO-8601 (например, 2023-10-15T10:15:30+01:00[Europe/Paris]):")
            while (true) {
                print("> ")
                try {
                    val startDate = ZonedDateTime.parse(readln().trim())
                    if (Validator.validateStartDate(startDate)) {
                        return startDate
                    }
                } catch (e: Exception) {
                    println("Ошибка: Введите дату в формате ISO-8601.")
                }
            }
        }

        /**
         * Чтение и валидация даты окончания работы.
         *
         * @return Валидная дата окончания работы или null.
         */
        private fun readValidEndDate(): LocalDate? {
            println("Введите дату окончания работы в формате ISO-8601 (или оставьте пустым):")
            while (true) {
                print("> ")
                val input = readln().trim()
                if (input.isEmpty()) {
                    return null
                }
                try {
                    return LocalDate.parse(input)
                } catch (e: Exception) {
                    println("Ошибка: Введите дату в формате ISO-8601 или оставьте пустым.")
                }
            }
        }

        /**
         * Чтение и валидация должности.
         * Регистр ввода не учитывается.
         *
         * @return Валидная должность или null.
         */
        private fun readValidPosition(): Position? {
            println("Введите должность работника из указанных (регистр не важен): ${Position.values().joinToString(", ")}")
            while (true) {
                print("> ")
                val input = readln().trim()
                if (input.isEmpty()) {
                    return null
                }
                try {
                    return Position.valueOf(input.uppercase())
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите одну из допустимых должностей: ${Position.values().joinToString()}")
                }
            }
        }

        /**
         * Чтение и валидация личных данных работника.
         *
         * @return Валидные личные данные.
         */
        private fun readValidPerson(): Person {
            println("Введите личные данные работника:")
            val birthday = readValidBirthday()
            val eyeColor = readValidEyeColor()
            val hairColor = readValidHairColor()
            val nationality = readValidNationality()

            return Person(birthday, eyeColor, hairColor, nationality)
        }

        /**
         * Чтение и валидация даты рождения.
         *
         * @return Валидная дата рождения.
         */
        private fun readValidBirthday(): LocalDateTime {
            println("Введите дату рождения в формате ISO-8601 (например, 2023-10-15T10:15:30):")
            while (true) {
                print("> ")
                try {
                    return LocalDateTime.parse(readln().trim())
                } catch (e: Exception) {
                    println("Ошибка: Введите дату в формате ISO-8601.")
                }
            }
        }

        /**
         * Чтение и валидация цвета глаз.
         * Регистр ввода не учитывается.
         *
         * @return Валидный цвет глаз или null.
         */
        private fun readValidEyeColor(): Color? {
            println("Введите цвет глаз из указанных (регистр не важен): ${Color.values().joinToString(", ")} (или оставьте пустым):")
            while (true) {
                print("> ")
                val input = readln().trim()
                if (input.isEmpty()) {
                    return null
                }
                try {
                    return Color.valueOf(input.uppercase())
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите один из допустимых цветов: ${Color.values().joinToString()}")
                }
            }
        }

        /**
         * Чтение и валидация цвета волос.
         * Регистр ввода не учитывается.
         *
         * @return Валидный цвет волос.
         */
        private fun readValidHairColor(): Color {
            println("Введите цвет волос из указанных (регистр не важен): ${Color.values().joinToString(", ")}:")
            while (true) {
                print("> ")
                val input = readln().trim()
                try {
                    return Color.valueOf(input.uppercase())
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите один из допустимых цветов: ${Color.values().joinToString()}")
                }
            }
        }

        /**
         * Чтение и валидация национальности.
         * Регистр ввода не учитывается.
         *
         * @return Валидная национальность.
         */
        private fun readValidNationality(): Country {
            println("Введите национальность из указанных (регистр не важен): ${Country.values().joinToString(", ")}:")
            while (true) {
                print("> ")
                val input = readln().trim()
                try {
                    return Country.valueOf(input.uppercase())
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите одну из допустимых национальностей: ${Country.values().joinToString()}")
                }
            }
        }
}