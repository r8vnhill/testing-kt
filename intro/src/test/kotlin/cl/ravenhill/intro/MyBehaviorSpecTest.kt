package cl.ravenhill.intro

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MyBehaviorSpecTest : BehaviorSpec({
    given("a string") {
        `when`("calculating its length") {
            then("it should be equal to the number of characters") {
                val str = "Hello, World!"
                str.length shouldBe 13
            }
        }
    }
})
