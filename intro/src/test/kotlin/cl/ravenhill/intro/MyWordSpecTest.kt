package cl.ravenhill.intro

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MyWordSpecTest : WordSpec({
    "String length" should {
        "be equal to the number of characters" {
            val str = "Hello, World!"
            str.length shouldBe 13
        }
    }
})
