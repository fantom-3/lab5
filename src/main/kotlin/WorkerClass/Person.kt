package WorkerClass
import Utils

import java.time.LocalDateTime


class Person(
    private val birthday: LocalDateTime,
    private val eyeColor: Color? = null,
    private val hairColor: Color,
    private val nationality: Country
    ) : Comparable<Person> {
        override fun compareTo(other: Person): Int {
            return this.birthday.compareTo(other.birthday)
        }

    override fun toString(): String {
        return "Person(birthday=$birthday, eyeColor=$eyeColor, hairColor=$hairColor, nationality=$nationality)"
    }
}