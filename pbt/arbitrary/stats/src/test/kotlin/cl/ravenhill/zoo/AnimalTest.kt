package cl.ravenhill.zoo

import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

/**
 * Generates an arbitrary instance of an [Animal], but with incorrect probabilities.
 *
 * This function aims to generate random animals but mistakenly assigns `Penguin` twice, making `Turtle` unreachable in
 * the selection process.
 *
 * Probability distribution:
 * - **50% Lion**
 * - **25% Penguin**
 * - **25% Penguin** (incorrectly duplicated, making `Turtle` impossible)
 *
 * ## Usage:
 * This function is useful for testing incorrect probability assignments.
 *
 * @return An [Arb] generator that produces random [Animal] instances with a faulty distribution.
 */
private fun arbAnimalWrong(): Arb<Animal> = arbitrary { (random, _) ->
    val x = random.nextDouble() // value between 0.0 and 1.0
    when {
        x < 0.50 -> Lion
        x < 0.75 -> Penguin
        else -> Penguin // Incorrect: Turtle is missing
    }
}

/**
 * Generates an arbitrary instance of an [Animal] with the correct probability distribution.
 *
 * This function randomly selects an animal with the following probabilities:
 * - **50% Lion**
 * - **25% Penguin**
 * - **25% Turtle**
 *
 * ## Usage:
 * This function ensures a balanced random generation of different animals.
 *
 * @return An [Arb] generator that produces random [Animal] instances with a correct distribution.
 */
private fun arbAnimalCorrect(): Arb<Animal> = arbitrary { (random, _) ->
    val x = random.nextDouble()
    when {
        x < 0.50 -> Lion
        x < 0.75 -> Penguin
        else -> Turtle
    }
}

class AnimalTest : FreeSpec({
    "Given an arbitrary animal" - {
        "when getting the animal with the incorrect implementation" - {
            "should return a lion, or a penguin, but never a turtle" {
                checkAll(arbAnimalWrong()) { animal ->
                    collect(animal)

                    // In this example, we don't perform any specific assertion,
                    // we're only interested in seeing the statistics printed by Kotest at the end.
                }
            }
        }

        "when getting the animal with the correct implementation" - {
            "should return a lion (~50%), a penguin (~25%), or a turtle (~25%)" {
                checkAll(arbAnimalCorrect()) { animal ->
                    collect(animal)

                    // In this example, we don't perform any specific assertion,
                    // we're only interested in seeing the statistics printed by Kotest at the end.
                }
            }
        }
    }
})
