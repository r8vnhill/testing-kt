package cl.ravenhill.lists

/**
 * Computes the average of a list of [Double] values.
 *
 * This function sums all the elements in the list and divides the result by the list's size. If the list is empty, this
 * will throw an [ArithmeticException] due to division by zero.
 *
 * ## Usage:
 * Use this function to calculate the mean of a numeric dataset.
 *
 * ### Example:
 * ```kotlin
 * val numbers = listOf(4.0, 8.0, 6.0)
 * val avg = average(numbers) // 6.0
 * ```
 *
 * @param list The list of [Double] values to average.
 * @return The arithmetic mean of the values in the list.
 * @throws ArithmeticException if the list is empty.
 */
fun average(list: List<Double>) =
    list.fold(0.0) { acc, number ->
        acc + number
    } / list.size
