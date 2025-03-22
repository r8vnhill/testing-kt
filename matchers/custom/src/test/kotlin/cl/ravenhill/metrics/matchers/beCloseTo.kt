package cl.ravenhill.metrics.matchers

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlin.math.abs

/**
 * Creates a custom matcher that checks whether a [Double] value is within a specified tolerance of an expected value.
 *
 * This matcher is useful when comparing floating-point numbers in tests, allowing for imprecision due to rounding
 * errors.
 *
 * ## Usage:
 * Use this matcher with testing frameworks that support [Matcher], such as Kotest.
 *
 * ### Example:
 * ```kotlin
 * 3.14 should beCloseTo(3.1415, 0.01)     // ✅ passes
 * 2.0 shouldNot beCloseTo(2.1, 0.05)      // ✅ passes
 * 2.0 should beCloseTo(2.1, 0.05)         // ❌ fails
 * ```
 *
 * @param expected The expected [Double] value.
 * @param tolerance The maximum allowed difference between the actual and expected values.
 * @return A [Matcher] that verifies whether the given [Double] is within the specified tolerance of the expected value.
 */
fun beCloseTo(expected: Double, tolerance: Double) = Matcher<Double> { value ->
    MatcherResult(
        passed = abs(value - expected) < tolerance,
        failureMessageFn = {
            "$value should be in the vicinity of $expected within a tolerance of $tolerance"
        },
        negatedFailureMessageFn = {
            "$value should not be in the vicinity of $expected within a tolerance of $tolerance"
        }
    )
}
