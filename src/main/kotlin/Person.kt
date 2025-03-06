import java.awt.Color
import java.time.LocalDate


class Person(
    val birthday: LocalDate,
    val eyeColor: Color? = null,
    val hairColor: Color,
    val nationality: Country
    ) {
}