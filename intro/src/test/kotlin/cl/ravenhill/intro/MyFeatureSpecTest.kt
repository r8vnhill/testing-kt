package cl.ravenhill.intro

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe

class MyFeatureSpecTest : FeatureSpec({
    feature("String length") {
        scenario("Should be equal to the number of characters") {
            val str = "Hello, World!"
            str.length shouldBe 13
        }
    }
})
