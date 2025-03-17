package cl.ravenhill.password

/**
 * Utility object for validating passwords based on customizable security requirements.
 *
 * This validator checks whether a given password meets the specified security constraints, such as minimum length,
 * presence of digits, uppercase/lowercase letters, and special characters.
 *
 * ## Usage:
 * This object provides a simple method to verify password strength by enforcing different rules.
 *
 * ### Example 1: Basic validation with default settings
 * ```kotlin
 * val isValid = PasswordValidator.isValid("Secure@123") // true
 * ```
 *
 * ### Example 2: Custom validation requirements
 * ```kotlin
 * val isValid = PasswordValidator.isValid(
 *     password = "password123",
 *     minLength = 10,
 *     requireDigit = true,
 *     requireLowerCase = true,
 *     requireUpperCase = false,
 *     requireSpecialChar = false
 * ) // true
 * ```
 */
object PasswordValidator {

    /** List of special characters required if `requireSpecialChar` is enabled. */
    private const val SPECIAL_CHARACTERS = "!@#$%^&*()-+"

    /**
     * Validates whether a given password meets the specified security constraints.
     *
     * @param password The password to be validated.
     * @param minLength The minimum required length of the password (default: 8).
     * @param requireDigit Whether at least one digit is required (default: true).
     * @param requireLowerCase Whether at least one lowercase letter is required (default: true).
     * @param requireUpperCase Whether at least one uppercase letter is required (default: true).
     * @param requireSpecialChar Whether at least one special character is required (default: true).
     * @return `true` if the password meets all specified requirements, otherwise `false`.
     */
    fun isValid(
        password: String,
        minLength: Int = 8,
        requireDigit: Boolean = true,
        requireLowerCase: Boolean = true,
        requireUpperCase: Boolean = true,
        requireSpecialChar: Boolean = true
    ) = password.length >= minLength &&
            (!requireDigit || password.any { it.isDigit() }) &&
            (!requireLowerCase || password.any { it.isLowerCase() }) &&
            (!requireUpperCase || password.any { it.isUpperCase() }) &&
            (!requireSpecialChar || password.any { it in SPECIAL_CHARACTERS })
}
