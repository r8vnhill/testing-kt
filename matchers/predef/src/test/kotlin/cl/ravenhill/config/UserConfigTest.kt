package cl.ravenhill.config

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class UserConfigTest : FreeSpec({

    "Given no custom configuration" - {
        "When using the default configuration" - {
            "Then it should match the expected default values" {
                defaultConfig shouldBe UserConfig()
            }
        }
    }

    "Given a custom configuration" - {
        "When compared to the default configuration" - {
            "Then it should not match" {
                val testConfig = UserConfig(theme = "dark", notificationsEnabled = false)
                testConfig shouldNotBe defaultConfig
            }
        }
    }
})
