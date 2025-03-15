package cl.ravenhill.geometry

/**
 * Represents a triangle defined by the lengths of its three sides.
 *
 * ## Usage:
 * This class can be used to represent the sides of a triangle, typically in geometric calculations or validations.
 *
 * ### Example 1: Creating a valid triangle
 * ```kotlin
 * val triangle = Triangle(3, 4, 5)
 * ```
 *
 * ### Example 2: Creating an equilateral triangle
 * ```kotlin
 * val equilateral = Triangle(5, 5, 5)
 * ```
 *
 * @property a The length of the first side.
 * @property b The length of the second side.
 * @property c The length of the third side.
 */
class Triangle(val a: Int, val b: Int, val c: Int)
