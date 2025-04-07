package Commands

import WorkerClass.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

object Add {
    fun addCommand() {
        println("Добавление нового работника:")

        // Генерация автоматических полей
        val id = Utils.generateId()
        val creationDate = Utils.generateCreationDate()

        // Ввод имени
        val name = readValidName()

        // Ввод координат
        val coordinates = readValidCoordinates()

        // Ввод зарплаты
        val salary = readValidSalary()

        // Ввод даты начала работы
        val startDate = readValidStartDate()

        // Ввод даты окончания работы
        val endDate = readValidEndDate()

        // Ввод должности
        val position = readValidPosition()

        // Ввод личных данных
        val person = readValidPerson()

        // Создание нового работника
        val worker = Worker(id, name, coordinates, creationDate, salary, startDate, endDate, position, person)

        // Добавление работника в коллекцию
        WorkerManager.addWorker(worker)
        println("Работник успешно добавлен в коллекцию.")
    }

        /**
         * Чтение и валидация имени работника.
         *
         * @return Валидное имя работника.
         */
        private fun readValidName(): String {
            println("Введите имя работника:")
            while (true) {
                print("> ")
                val name = readln().trim()
                if (Validator.validateName(name)) {
                    return name
                }
                println("Ошибка: Имя не может быть пустым. Повторите ввод.")
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
         *
         * @return Валидная должность или null.
         */
        private fun readValidPosition(): Position? {
            println("Введите должность работника из указанных: ${Position.values().joinToString(", ")}")
            while (true) {
                print("> ")
                val input = readln().trim()
                if (input.isEmpty()) {
                    return null
                }
                try {
                    return Position.valueOf(input)
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите одну из допустимых должностей.")
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
         *
         * @return Валидный цвет глаз или null.
         */
        private fun readValidEyeColor(): Color? {
            println("Введите цвет глаз из указанных: ${Color.values().joinToString(", ")} (или оставьте пустым):")
            while (true) {
                print("> ")
                val input = readln().trim()
                if (input.isEmpty()) {
                    return null
                }
                try {
                    return Color.valueOf(input)
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите один из допустимых цветов.")
                }
            }
        }

        /**
         * Чтение и валидация цвета волос.
         *
         * @return Валидный цвет волос.
         */
        private fun readValidHairColor(): Color {
            println("Введите цвет волос из указанных: ${Color.values().joinToString(", ")}:")
            while (true) {
                print("> ")
                try {
                    return Color.valueOf(readln().trim())
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите один из допустимых цветов.")
                }
            }
        }

        /**
         * Чтение и валидация национальности.
         *
         * @return Валидная национальность.
         */
        private fun readValidNationality(): Country {
            println("Введите национальность из указанных: ${Country.values().joinToString(", ")}:")
            while (true) {
                print("> ")
                try {
                    return Country.valueOf(readln().trim())
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: Введите одну из допустимых национальностей.")
                }
            }
        }
}