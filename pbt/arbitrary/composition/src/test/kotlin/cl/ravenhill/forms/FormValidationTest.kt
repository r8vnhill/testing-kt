package cl.ravenhill.forms

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.string.shouldHaveMinLength
import io.kotest.matchers.string.shouldMatch
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string
import io.kotest.property.arbitrary.stringPattern
import io.kotest.property.checkAll

class FormValidationTest : FreeSpec({
    "Given a user input" - {
        "when validating it" - {
            "then it should have at least 3 characters" {
                checkAll(arbUserInput()) { user ->
                    user.name shouldHaveMinLength 3
                }
            }

            "then it should have the correct format" {
                checkAll(arbUserInput()) { user ->
                    user.email shouldMatch Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
                }
            }

            "then it should be between 18 and 99" {
                checkAll(arbUserInput()) { user ->
                    (user.age shouldBeGreaterThanOrEqual 18) and
                            (user.age shouldBeLessThanOrEqual 99)
                }
            }
        }
    }
})

/**
 * Generates an arbitrary valid name with a length between 3 and 20 characters.
 *
 * This function is useful for property-based testing to generate realistic names.
 *
 * @return An [Arb] generator that produces random names.
 */
private fun arbName(): Arb<String> = Arb.string(3..20)

/**
 * Generates an arbitrary valid email address following a standard pattern.
 *
 * This function ensures that generated emails match a common format, making it useful for testing email validation.
 *
 * @return An [Arb] generator that produces random email addresses.
 */
private fun arbEmail(): Arb<String> = Arb.stringPattern("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

/**
 * Generates an arbitrary valid age within the allowed range of 18 to 99 years.
 *
 * This function is useful for testing scenarios where user age restrictions apply.
 *
 * @return An [Arb] generator that produces random ages.
 */
private fun arbAge(): Arb<Int> = Arb.int(18..99)

/**
 * Generates an arbitrary [UserInput] instance with randomized name, email, and age values.
 *
 * This function binds three generators (`arbName`, `arbEmail`, `arbAge`) to produce valid user input instances.
 *
 * ## Usage:
 * This generator is useful for property-based testing scenarios that require randomized user data.
 *
 * @return An [Arb] generator that produces random [UserInput] instances.
 */
private fun arbUserInput(): Arb<UserInput> =
    Arb.bind(arbName(), arbEmail(), arbAge()) { name, email, age -> UserInput(name, email, age) }
