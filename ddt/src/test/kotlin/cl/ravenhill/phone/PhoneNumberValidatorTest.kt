package cl.ravenhill.phone

import cl.ravenhill.phone.PhoneNumberValidator.isValidPhoneNumber
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PhoneNumberValidatorTest : FreeSpec({
    "Given a phone number" - {
        "when validating it" - {
            "then it should return true if it is valid and false if it is invalid" - {
                withData(
                    "12345678" to true,
                    "123 4567 8910" to true,
                    "+123-4567-8910" to true,
                    "-123-4567-8910" to false,
                    " 123-4567-8910" to false,
                    "123" to false,
                ) { (phoneNumber, expected) ->
                    isValidPhoneNumber(phoneNumber) shouldBe expected
                }
            }
        }
    }
})
