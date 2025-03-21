package cl.ravenhill.maps

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

class IntStringMapTest : FreeSpec({

    "Given a randomly generated map of Int to String" - {
        "when generating a new map" - {
            "then it should contain at least one entry" {
                checkAll(arbIntStringMap()) { map ->
                    map.size shouldBeGreaterThan 0
                }
            }

            "then it should not contain duplicate keys" {
                checkAll(arbIntStringMap()) { map ->
                    map.keys shouldHaveSize map.size
                }
            }

            "then all string values should have at least one character" {
                checkAll(arbIntStringMap()) { map ->
                    map.values.forEach { value ->
                        value.length shouldBeGreaterThan 0
                    }
                }
            }

            "then all string values should have at most 100 characters" {
                checkAll(arbIntStringMap()) { map ->
                    map.values.forEach { value ->
                        value.length shouldBeLessThanOrEqual 100
                    }
                }
            }

            "then the total number of entries should be at most 100" {
                checkAll(arbIntStringMap()) { map ->
                    map.size shouldBeLessThanOrEqual 100
                }
            }
        }
    }
})

private typealias IntStringMap = Map<Int, String>

/**
 * Generates an arbitrary map of integers to strings.
 *
 * This generator creates random maps where:
 * - Each key is a randomly generated [Int].
 * - Each value is a randomly generated [String] composed of Unicode characters (from code points 0x0000 to 0xFFFF),
 *   with a length between 1 and 99 characters.
 * - The total number of key-value pairs ranges from 1 to 99.
 *
 * ## Usage:
 * This generator is useful for property-based testing of functions or components that process maps with heterogeneous
 * key-value content.
 *
 * ## Note:
 * The key generation ensures uniqueness by storing entries in a [MutableMap], and retries are performed if a duplicate
 * key is generated.
 *
 * @return An [Arb] generator that produces random [IntStringMap] instances.
 */
private fun arbIntStringMap(): Arb<IntStringMap> = arbitrary { (random, seed) ->
    val map = mutableMapOf<Int, String>()
    val size = random.nextInt(1, 100)
    while (map.size < size) {
        val key = random.nextInt()
        val stringSize = random.nextInt(1, 100)
        val value = List(stringSize) {
            random.nextInt(0x0000, 0xFFFF).toChar()
        }.joinToString("")
        map[key] = value
    }
    map
}
