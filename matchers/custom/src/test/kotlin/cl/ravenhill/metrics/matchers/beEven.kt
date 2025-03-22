package cl.ravenhill.metrics.matchers

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult

/**
 * Creates a custom matcher that checks if an integer is even.
 *
 * This matcher can be used in tests to assert that a given integer is divisible by 2.
 *
 * ## Usage:
 * Useful in custom assertions with testing frameworks that support [Matcher], such as Kotest.
 *
 * ### Example:
 * ```kotlin
 * 4 should beEven()     // ✅ passes
 * 3 shouldNot beEven()  // ✅ passes
 * 3 should beEven()     // ❌ fails with "3 should be even"
 * ```
 *
 * @return A [Matcher] that verifies whether an [Int] is even.
 */
fun beEven() = Matcher<Int> { value ->
    MatcherResult(
        passed = value % 2 == 0,
        failureMessageFn = { "$value should be even" },
        negatedFailureMessageFn = { "$value should not be even" }
    )
}
