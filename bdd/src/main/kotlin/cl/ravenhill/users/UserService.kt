package cl.ravenhill.users

/**
 * A service class for managing user registration and authentication.
 *
 * The `UserService` class provides methods to register new users and authenticate existing ones. Usernames must be
 * unique, and attempts to register a duplicate username will raise an error. Passwords are stored in a private map, and
 * the class only exposes usernames through the public `users` property.
 *
 * ## Usage:
 * The `UserService` class can be used to register and authenticate users in applications where basic user management is
 * needed.
 *
 * ### Example 1: Registering and Authenticating Users
 * ```kotlin
 * val userService = UserService()
 * userService.register("john_doe", "password123")
 * println(userService.authenticate("john_doe", "password123"))  // Outputs: true
 * println(userService.users)  // Outputs: [john_doe]
 * ```
 *
 * @property users A read-only list of registered usernames.
 */
class UserService {

    // Private map storing usernames as keys and passwords as values.
    private val _users = mutableMapOf<String, String>()

    /**
     * A list of registered usernames.
     *
     * This read-only property returns a list of all registered usernames.
     */
    val users: List<String>
        get() = _users.keys.toList()

    /**
     * Registers a new user with a username and password.
     *
     * This method adds a new username-password pair to the user map. The username must be unique; otherwise, an
     * `IllegalArgumentException` is thrown.
     *
     * @param username The unique username to register.
     * @param password The password for the new user.
     * @throws IllegalArgumentException if the username already exists in `_users`.
     */
    fun register(username: String, password: String) {
        require(username !in _users) { "User already exists" }
        _users[username] = password
    }

    /**
     * Authenticates a user based on their username and password.
     *
     * This method verifies that the username exists and that the provided password matches the stored password for that
     * username.
     *
     * @param username The username to authenticate.
     * @param password The password to authenticate.
     * @return `true` if authentication is successful; `false` otherwise.
     * @throws IllegalArgumentException if the username does not exist in `_users`.
     */
    fun authenticate(username: String, password: String): Boolean {
        require(username in _users) { "User not found" }
        return _users[username] == password
    }
}
