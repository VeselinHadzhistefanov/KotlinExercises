package Exercises

import java.lang.reflect.Field
import java.util.*
/*
да се напише фунция strings с неопределен брой параметри. функцията да връща едномерен масив от стрингове, като изважда всички стрингове от всички вложени или подадени обекти, както и обектите които те съдържат.

ако елемента е стринг той се включва в резултата
ако елемента е обект всеки от неговите полета които са стрингове и са различни от null попадат в резултата
ако елемента е обект и има полета обекти различни от null - техните стрингове се изваждат се се добавят към резултата и т.н. - тоест се изливчат се стринговете при всякаква дължина на влагане на обектите
ако елемента е масив всеки от елементите му се обработва по горните првила
Да се извлекат стрингове от произволна дълбочина на влагане на обектите.

Пример:
Employee e = new Employee("Ivan");
String sa[] = {"hello", "again");
var res = strings("now", sa, e);    // => ["now", "hello", "again", "ivan"]
*/

fun main(args: Array<String>) {
    val e = Employee("Ivan");
    val sa: ArrayList<String> = arrayListOf("hello", "again")
    val res = strings("now", sa, e);
    print(res)
}

fun strings(vararg objects: Any): ArrayList<String> {
    val strings = ArrayList<String>()
    val objectsList = ArrayList<Any>()

    for (o in objects) {
        if (o is ArrayList<*>) {
            objectsList.addAll(o)
        } else {
            objectsList.add(o)
        }
    }

    for (o in objectsList) {
        if (o is String) {
            strings.add(o)
            continue
        }

        val fields: Array<Field> = o.javaClass.declaredFields
        for (field in fields) {
            field.isAccessible = true

            if (field.type.equals(String::class.java)) {
                strings.add(field.get(o) as String)
            } else {
                val stringsInObject = strings(field.get(o))
                strings.addAll(stringsInObject)
            }
        }
    }

    return strings
}