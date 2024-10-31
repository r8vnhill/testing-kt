package cl.ravenhill.lists

fun average(list: List<Double>) =
    list.fold(0.0) { acc, number ->
        acc + number
    } / list.size
