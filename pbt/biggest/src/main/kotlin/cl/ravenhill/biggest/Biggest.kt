package cl.ravenhill.biggest

fun biggest(list: List<Int>) =
    list.reduce { acc, number -> if (number > acc) number else acc }
