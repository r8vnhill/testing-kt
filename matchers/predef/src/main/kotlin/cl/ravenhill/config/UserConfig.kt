package cl.ravenhill.config

/**
 * Represents user preferences and configuration options.
 *
 * This class stores common user settings such as theme preference and notification toggles.
 * It provides overridden `toString`, `equals`, and `hashCode` implementations for use in comparisons, logging, or
 * caching.
 *
 * ## Usage:
 * Use this class to encapsulate customizable user settings across an application.
 *
 * ### Example:
 * ```kotlin
 * val config = UserConfig(theme = "dark", notificationsEnabled = false)
 * println(config) // Output: UserConfig(theme='dark', notificationsEnabled=false)
 * ```
 *
 * @property theme The selected UI theme (e.g., `"light"` or `"dark"`).
 * @property notificationsEnabled Whether the user has enabled notifications.
 */
class UserConfig(
    val theme: String = "light",
    val notificationsEnabled: Boolean = true
) {
    override fun toString(): String =
        "UserConfig(theme='$theme', notificationsEnabled=$notificationsEnabled)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserConfig) return false

        if (theme != other.theme) return false
        if (notificationsEnabled != other.notificationsEnabled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = theme.hashCode()
        result = 31 * result + notificationsEnabled.hashCode()
        return result
    }
}

/**
 * Provides the default user configuration.
 *
 * @return A [UserConfig] instance with the default values (`theme = "light"`, `notificationsEnabled = true`).
 */
val defaultConfig get() = UserConfig()
