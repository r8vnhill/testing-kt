package cl.ravenhill.password

import cl.ravenhill.password.PasswordValidator.isValid
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PasswordValidatorTest : FreeSpec({
    "Given a password" - {
        "when validating it" - {
            "then it should return true if it is strong and false if it is weak" - {
                withData(
                    "@" to true, "" to false, "!" to true, "\$" to true
                ) { (maybeSpecialChar, containsSpecialChar) ->
                    withData(
                        "a" to true, "b" to true, "c" to true, "" to false,
                    ) { (maybeLowerCase, containsLowerCase) ->
                        withData(
                            "A" to true, "B" to true, "C" to true, "" to false,
                        ) { (maybeUpperCase, containsUpperCase) ->
                            withData(
                                "1" to true, "2" to true, "3" to true, "" to false,
                            ) { (maybeDigit, containsDigit) ->
                                withData(1, 2, 3, 4) { minLength ->
                                    val password = "$maybeLowerCase$maybeUpperCase$maybeDigit$maybeSpecialChar"
                                    val expected = containsLowerCase && containsUpperCase &&
                                            containsDigit && containsSpecialChar &&
                                            password.length >= minLength
                                    isValid(
                                        password,
                                        minLength,
                                        requireLowerCase = true,
                                        requireUpperCase = true,
                                        requireDigit = true,
                                        requireSpecialChar = true
                                    ) shouldBe expected
                                }
                            }
                        }
                    }
                }
            }
        }
    }
})
