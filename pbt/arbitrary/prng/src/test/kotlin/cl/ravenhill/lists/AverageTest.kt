package cl.ravenhill.lists

import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.doubles.shouldBeGreaterThanOrEqual
import io.kotest.matchers.doubles.shouldBeLessThan
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

typealias ListAndAverage = Pair<MutableList<Double>, Double>

@OptIn(ExperimentalKotest::class)
class AverageTest : FreeSpec({
    "Given a list of integers" - {
        "when calculating the average of a non-empty list" - {
            ("should return the sum of the elements divided by the number of " +
                    "elements") {
                checkAll(
                    PropTestConfig(seed = 123456),
                    arbIntListAndAverage()
                ) { (list, average) ->
                    average(list)
                        .shouldBeGreaterThanOrEqual(average - 0.0001)
                        .shouldBeLessThan(average + 0.0001)
                }
            }
        }
    }
})

/**
 * Generates arbitrary data consisting of a list of [Double] values and an approximate average.
 *
 * This generator produces a pair—referred to as `ListAndAverage`—containing:
 * 1. A randomly sized list (1 to 99 elements) of real numbers between 1.0 and 100.0.
 * 2. An approximate average calculated using a *different* formula than the one under test:
 *    instead of summing all elements and dividing by the list size, it divides each number by the size first and
 *    accumulates the result.
 *
 * This intentional difference introduces minor floating-point rounding errors, which is beneficial when used in
 * property-based testing to avoid coupling the generator with the logic being tested.
 *
 * ## Rationale:
 * By calculating the average using a different strategy, we reduce the risk of false positives in tests.
 * We ensure the function under test (`average`) is evaluated independently of its input generator.
 * In tests, results are typically compared within a small tolerance (e.g., ±0.0001) to account for this difference.
 *
 * @return An [Arb] generator that produces a pair of a list and its approximate average.
 */
private fun arbIntListAndAverage(): Arb<ListAndAverage> = arbitrary { (random, seed) ->
    val list = mutableListOf<Double>()
    val size = random.nextInt(1, 100)
    var average = 0.0
    repeat(size) {
        val number = random.nextDouble(1.0, 100.0)
        list += number
        average += number / size
    }
    list to average
}
