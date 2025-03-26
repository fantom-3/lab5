package Commands

import WorkerClass.*
import java.io.FileWriter

/**
 * Команда для сохранения коллекции в XML-файл.
 */
object SaveCommand {

    /**
     * Выполняет сохранение коллекции в файл.
     * @param filename Имя файла для сохранения (если null, используется значение по умолчанию)
     */
    fun execute(filename: String? = null) {
        val targetFile = filename ?: "workers.xml"

        try {
            FileWriter(targetFile).use { writer ->
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                writer.write("<workers>\n")

                WorkerManager.collection.forEach { worker ->
                    writer.write(workerToXml(worker))
                }

                writer.write("</workers>")
            }

            println("Коллекция успешно сохранена в файл $targetFile")
        } catch (e: SecurityException) {
            println("Ошибка: нет прав для записи в файл $targetFile")
        } catch (e: Exception) {
            println("Ошибка при сохранении файла: ${e.message}")
        }
    }

    private fun workerToXml(worker: Worker): String {
        return """
        |  <worker>
        |    <id>${worker.id}</id>
        |    <name>${escapeXml(worker.name)}</name>
        |    <coordinates>
        |      <x>${worker.coordinates.x}</x>
        |      <y>${worker.coordinates.y}</y>
        |    </coordinates>
        |    <creationDate>${worker.creationDate}</creationDate>
        |    <salary>${worker.salary}</salary>
        |    <startDate>${worker.startDate}</startDate>
        |    <endDate>${worker.endDate ?: ""}</endDate>
        |    <position>${worker.position ?: ""}</position>
        |    <person>
        |      <birthday>${worker.person.birthday}</birthday>
        |      <eyeColor>${worker.person.eyeColor ?: ""}</eyeColor>
        |      <hairColor>${worker.person.hairColor}</hairColor>
        |      <nationality>${worker.person.nationality}</nationality>
        |    </person>
        |  </worker>
        """.trimMargin() + "\n"
    }

    private fun escapeXml(text: String): String {
        return text.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;")
    }
}