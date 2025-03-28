package cl.ravenhill.config

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class UserConfigTest : FreeSpec({

    "Given no custom configuration" - {
        "When using the default configuration" - {
            "Then it should match the expected default values" {
                defaultConfig shouldBe UserConfig()
            }
        }
    }
})
