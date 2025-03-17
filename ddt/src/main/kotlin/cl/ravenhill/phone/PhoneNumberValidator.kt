package cl.ravenhill.phone

/**
 * Utility object for validating phone numbers based on basic format constraints.
 *
 * This validator checks whether a given phone number follows a valid format, allowing an optional '+' prefix, digits
 * only, and a length constraint.
 *
 * ## Usage:
 * This object provides a method to verify phone number validity by enforcing certain rules.
 *
 * ### Example 1: Valid phone numbers
 * ```kotlin
 * val isValid = PhoneNumberValidator.isValidPhoneNumber("+123456789") // true
 * val isValid = PhoneNumberValidator.isValidPhoneNumber("987654321")  // true
 * ```
 *
 * ### Example 2: Invalid phone numbers
 * ```kotlin
 * val isValid = PhoneNumberValidator.isValidPhoneNumber("+12-34-56") // false (too short)
 * val isValid = PhoneNumberValidator.isValidPhoneNumber("abc123456") // false (contains letters)
 * ```
 */
object PhoneNumberValidator {

    /**
     * Validates whether a given phone number follows basic formatting rules.
     *
     * The phone number must:
     * - Start with either a '+' sign or a digit.
     * - Contain only digits after removing spaces and hyphens.
     * - Have a length between 8 and 11 digits (excluding '+').
     *
     * @param phoneNumber The phone number to be validated.
     * @return `true` if the phone number meets the specified requirements, otherwise `false`.
     */
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        if (!phoneNumber.startsWith("+") && !phoneNumber.first().isDigit())
            return false
        // Remove all hyphens and spaces from the number
        val cleanedNumber = phoneNumber.replace("[- ]".toRegex(), "")
        // Check if the number starts with + and remove it
        val hasPlusPrefix = cleanedNumber.startsWith("+")
        val numberWithoutPlus =
            if (hasPlusPrefix) cleanedNumber.substring(1)
            else cleanedNumber
        // Ensure the number contains only digits
        if (!numberWithoutPlus.all { it.isDigit() }) return false
        // Validate the length of the number
        return numberWithoutPlus.length in 8..11
    }
}
