package Homework21

fun main(args: Array<String>) {
    /*
    https://gist.github.com/gozzoo/0072bd8dd56045cfa94d99cb0b8e7ddb
    Задача 1. Валидно float число
    да се разработи функция isFloat която поучава като параметър стринг и връща true ако стринга съдрържа валидно float число.

    Забележки:
    да не се ползва regular expression
    стринга да не се превръща в число с вградените функции
    */

    println(isFloat("0.1")) // => true
    println(isFloat(".3")) // => true
    println(isFloat("-0.1")) // => true
    println(isFloat("0.1.1")) // => false
    println(isFloat("+0.1")) // => true
    println(isFloat("abc")) // => false
    println(isFloat("1 a")) // => false
    println(isFloat("2e10")) // => true ToDo fix bug
    println(isFloat("1.234e2")) // => true
    println(isFloat("1.234e-2")) // => true
    println(isFloat("+1.234e-2")) // => true
    println(isFloat("1.2e2.5")) // => false
    println(isFloat("1.2e5e3")) // => false
    println(isFloat("1.2e")) // => false
    println(isFloat("e5")) // => false
}


fun isFloat(s: String): Boolean {
    val decimalPosition = s.indexOf('.')

    if (decimalPosition == -1 || decimalPosition == s.length - 1) {
        return false
    }

    val wholePart = s.substring(0, decimalPosition)

    try {
        wholePart.toInt()
    } catch (e: NumberFormatException) {
        if (wholePart != "") return false
    }

    var exponentPosition = s.indexOf('e')
    if (exponentPosition != -1) {
        if (exponentPosition < decimalPosition + 2 || exponentPosition == s.length - 1) {
            return false
        }
        val exponentPart = s.substring(exponentPosition + 1)
        try {
            exponentPart.toInt()
        } catch (e: NumberFormatException) {
            return false
        }
    } else {
        exponentPosition = s.length
    }

    val decimalPart = s.substring(decimalPosition + 1, exponentPosition)
    val decimalPartInt: Int

    decimalPartInt = try {
        decimalPart.toInt()
    } catch (e: NumberFormatException) {
        return false
    }

    return decimalPartInt >= 0
}