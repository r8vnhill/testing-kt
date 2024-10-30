package cl.ravenhill.users

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

private const val USER = "ichigo"
private const val PASSWORD = "shinigami123"

class UserServiceTest : FreeSpec({
    lateinit var userService: UserService

    beforeEach {
        userService = UserService()
    }

    "A user service" - {
        "when registering a new user" - {
            "should add the user to the database" {
                userService.register(USER, PASSWORD)
                userService.users.contains(USER) shouldBe true
            }
        }

        "when authenticating an existing user" - {
            "should return true for valid credentials" {
                userService.register(USER, PASSWORD)
                userService.authenticate(USER, PASSWORD) shouldBe true
            }
        }

        "when registering an existing user" - {
            "should throw an exception" {
                userService.register(USER, PASSWORD)
                shouldThrow<IllegalArgumentException> {
                    userService.register(USER, PASSWORD)
                }.message shouldBe "User already exists"
            }
        }

        "when authenticating a non-existing user" - {
            "should return false" {
                val wrongPassword = "wrongpassword"
                wrongPassword shouldNotBe PASSWORD
                userService.register(USER, PASSWORD)
                userService.authenticate(USER, "wrongpassword") shouldBe false
            }

            "should throw an exception" {
                shouldThrow<IllegalArgumentException> {
                    userService.authenticate("nonexistent", "password")
                }.message shouldBe "User not found"
            }
        }
    }
})
