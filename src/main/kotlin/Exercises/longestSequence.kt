package Exercises
/*
да се напише функция която получава като праметър масив и връща масив съдържащ най дългата поредица от последователни числа
0, 5, 1, 2, 3, 4, 5, 2, 8, 9, 10 -> 0, 1, 2, 3, 4, 5
*/

fun main(args: Array<String>) {

    print(getSequence(arrayOf(0, 5, 1, 2, 3, 4, 5, 2, 8, 9, 10)).contentToString())
}

fun getSequence(arr: Array<Int>): Array<Int>? {
    val numberSet: MutableSet<Int> = HashSet()
    numberSet.addAll(arr.asList())

    val sortedArray: List<Int> = ArrayList(numberSet)
    sortedArray.sortedWith(Comparator.naturalOrder())

    var maxLen = 0
    var maxOffset = 0
    for (i in sortedArray.indices) {
        val offset = sortedArray[i]
        var idx = 0
        while (sortedArray[i + idx] == offset + idx && i + idx < sortedArray.size - 1) {
            idx++
        }

        if (idx > maxLen) {
            maxLen = idx
            maxOffset = offset
        }
    }
    val sequence = Array(maxLen) { i -> i + maxOffset }

    return sequence
}






