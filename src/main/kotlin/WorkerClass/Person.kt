package WorkerClass

import java.time.LocalDateTime


class Person(
    val birthday: LocalDateTime,
    val eyeColor: Color? = null,
    val hairColor: Color,
    val nationality: Country
    ) : Comparable<Person> {
        override fun compareTo(other: Person): Int {
            return this.birthday.compareTo(other.birthday)
        }

    override fun toString(): String {
        return "Person(birthday=$birthday, eyeColor=$eyeColor, hairColor=$hairColor, nationality=$nationality)"
    }
}