package ru.melonhell.uma.common.internal.core.utilts.data.math


data class Transform(
    val position: Vector3D = Vector3D.ZERO,
    val rotation: QuaternionRotation = QuaternionRotation.IDENTITY,
) {
    constructor(x: Double, y: Double, z: Double, rotation: QuaternionRotation) : this(Vector3D(x, y, z), rotation)
    companion object {
        val ZERO = Transform()
    }
}