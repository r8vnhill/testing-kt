package cl.ravenhill.metrics

import cl.ravenhill.metrics.matchers.beEven
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.should

class IdentifierGeneratorTest : FreeSpec({
    "Given an identifier generator for performance events" - {
        val generator = IdentifierGenerator()

        "when it generates a batch of IDs" - {
            "then all IDs should be even" - {
                withData(1, 2, 10) { size ->
                    val ids = generator.generateBatch(size)
                    withData(ids) { id ->
                        id should beEven()
                    }
                }
            }
        }
    }
})
