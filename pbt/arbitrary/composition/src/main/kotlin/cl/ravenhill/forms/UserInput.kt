package cl.ravenhill.forms

/**
 * Represents user-provided input containing basic personal details.
 *
 * This class holds essential information such as name, email, and age, typically used in form submissions or user
 * registration processes.
 *
 * ## Usage:
 * This class can be instantiated to store user input before further processing.
 *
 * ### Example:
 * ```kotlin
 * val input = UserInput(name = "Joe", email = "henshin@viewtiful.com", age = 25)
 * ```
 *
 * @property name The user's full name.
 * @property email The user's email address.
 * @property age The user's age.
 */
class UserInput(val name: String, val email: String, val age: Int)
