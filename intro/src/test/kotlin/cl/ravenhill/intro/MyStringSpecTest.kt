package cl.ravenhill.intro

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MyStringSpecTest : StringSpec({
    "String length should be equal to the number of characters" {
        val str = "Hello, World!"
        str.length shouldBe 13
    }
})
