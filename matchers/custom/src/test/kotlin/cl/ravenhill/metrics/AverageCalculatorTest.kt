package cl.ravenhill.metrics

import cl.ravenhill.metrics.matchers.beCloseTo
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.should

class AverageCalculatorTest : FreeSpec({

    "Given a calculator of event averages" - {
        "when it processes a list of durations in milliseconds" - {
            val durations = listOf(100.0, 102.0, 98.0, 101.0)

            "then the average should be close to the expected baseline" - {
                val result = AverageCalculator.average(durations)
                result should beCloseTo(100.0, 1.5)
            }
        }
    }
})
