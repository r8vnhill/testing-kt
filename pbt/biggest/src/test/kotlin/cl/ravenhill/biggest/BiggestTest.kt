package cl.ravenhill.biggest

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.checkAll

class BiggestTest : FreeSpec({
    "Given an integer list" - {
        "when getting the biggest element of a non-empty list" - {
            "should return the last element of the sorted list" {
                checkAll(Arb.list(Arb.int(), 1..100)) { list ->
                    @Suppress("SimplifiableCallChain")
                    biggest(list) shouldBe list.sorted().last()
                }
            }
        }

        "when getting the biggest element of an empty list" - {
            "should return the smallest integer" {
                biggest(emptyList()) shouldBe Int.MIN_VALUE
            }
        }
    }
})
