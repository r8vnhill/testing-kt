package cl.ravenhill.geometry

import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

/**
 * Generates an arbitrary instance of a [Triangle] with varying side lengths.
 *
 * This function creates random triangles with different types based on probability distribution:
 * - **50% Equilateral:** All sides are equal.
 * - **30% Isosceles:** Two sides are equal, and the third side is different.
 * - **20% Scalene:** All three sides are different.
 *
 * ## Usage:
 * This function is typically used in property-based testing to generate diverse triangle instances.
 *
 * @return An [Arb] generator that produces random [Triangle] instances.
 */
private fun arbTriangle(): Arb<Triangle> = arbitrary { (random, _) ->
    val type = random.nextDouble()
    val a = random.nextInt(1, 10)
    when {
        type < 0.5 -> Triangle(a, a, a) // 50% Equilateral - All sides must be equal
        type < 0.8 -> { // 30% Isosceles - The third side must be different
            var b = random.nextInt(1, 10)
            while (b == a) {
                b = random.nextInt(1, 10)
            }
            Triangle(a, a, b)
        }
        else -> { // 20% Scalene - All sides must be different
            var b = random.nextInt(1, 10)
            while (b == a) {
                b = random.nextInt(1, 10)
            }
            var c = random.nextInt(1, 10)
            while (c == a || c == b) {
                c = random.nextInt(1, 10)
            }
            Triangle(a, b, c)
        }
    }
}

class TriangleTest : FreeSpec({
    "Given an arbitrary triangle" - {
        "when getting the triangle" - {
            "should return an equilateral (~50%), an isosceles (~30%), or a scalene (~20%) triangle" {
                checkAll(arbTriangle()) { triangle ->
                    val triangleType = when {
                        triangle.a == triangle.b && triangle.b == triangle.c -> "Equilateral"
                        triangle.a == triangle.b ||
                                triangle.b == triangle.c ||
                                triangle.a == triangle.c -> "Isosceles"

                        else -> "Scalene"
                    }
                    collect(triangleType)
                }
            }
        }
    }
})
