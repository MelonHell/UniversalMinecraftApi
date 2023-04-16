package ru.melonhell.uma.common.internal.core.utilts.data.math

import kotlin.math.sqrt

data class Vector3D(
    val x: Double,
    val y: Double,
    val z: Double,
) {

    companion object {
        val ZERO = Vector3D(0.0, 0.0, 0.0)
    }

    val length by lazy { sqrt(x * x + y * y + z * z) }
    val normalized by lazy { divide(length) }

    fun add(other: Vector3D) = Vector3D(x + other.x, y + other.y, z + other.z)
    operator fun plus(other: Vector3D) = add(other)
    fun subtract(other: Vector3D) = Vector3D(x - other.x, y - other.y, z - other.z)
    operator fun minus(other: Vector3D) = subtract(other)
    fun multiply(value: Double) = Vector3D(x * value, y * value, z * value)
    operator fun times(value: Double) = multiply(value)

    fun divide(value: Double) = Vector3D(x / value, y / value, z / value)
    operator fun div(value: Double) = divide(value)
}