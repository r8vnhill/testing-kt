package cl.ravenhill.intro

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MyFunSpecTest : FunSpec({
    context("String length") {
        test("Should be equal to the number of characters") {
            val str = "Hello, World!"
            str.length shouldBe 13
        }
    }
})
