package cl.ravenhill.metrics

/**
 * Utility class for generating simple numeric identifiers.
 *
 * This generator produces a batch of sequential identifiers where each value is twice its index.
 * It's useful for scenarios where unique, predictable identifiers are needed for testing or placeholder data.
 *
 * ## Usage:
 * Create an instance and call `generateBatch` with the desired number of identifiers.
 *
 * ### Example:
 * ```kotlin
 * val generator = IdentifierGenerator()
 * val ids = generator.generateBatch(5)
 * println(ids) // Output: [0, 2, 4, 6, 8]
 * ```
 *
 * @constructor Creates a new instance of the identifier generator.
 */
class IdentifierGenerator {

    /**
     * Generates a list of numeric identifiers.
     *
     * Each identifier is twice its index in the list.
     *
     * @param size The number of identifiers to generate.
     * @return A list of integers of the form [0, 2, 4, ..., (size - 1) * 2].
     */
    fun generateBatch(size: Int) = List(size) { it * 2 }
}
