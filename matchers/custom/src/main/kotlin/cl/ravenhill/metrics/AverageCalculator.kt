package cl.ravenhill.metrics

/**
 * Utility object for calculating averages from numeric values.
 *
 * Provides a method to compute the arithmetic mean of a list of [Double] values.
 *
 * ## Usage:
 * Useful in statistical computations or data analysis tasks.
 *
 * ### Example:
 * ```kotlin
 * val result = AverageCalculator.average(listOf(4.0, 8.0, 15.0))
 * println(result) // Output: 9.0
 * ```
 */
object AverageCalculator {

    /**
     * Computes the arithmetic mean of a list of [Double] values.
     *
     * @param values The list of numbers to average.
     * @return The average of the values, or `NaN` if the list is empty.
     */
    fun average(values: List<Double>) = values.average()
}
