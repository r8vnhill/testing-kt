package cl.ravenhill.lists

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.doubles.shouldBeGreaterThanOrEqual
import io.kotest.matchers.doubles.shouldBeLessThan
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

typealias ListAndAverage = Pair<MutableList<Double>, Double>

class AverageTest : FreeSpec({
    "Given a list of integers" - {
        "when calculating the average of a non-empty list" - {
            ("should return the sum of the elements divided by the number of " +
                    "elements") {
                checkAll(arbIntListAndAverage()) { (list, average) ->
                    average(list)
                        .shouldBeGreaterThanOrEqual(average - 0.0001)
                        .shouldBeLessThan(average + 0.0001)
                }
            }
        }
    }
})

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