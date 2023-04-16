package ru.melonhell.uma.common.internal.core.utilts.data.math

import kotlin.math.cos
import kotlin.math.sin

data class QuaternionRotation(
    val qx: Double,
    val qy: Double,
    val qz: Double,
    val qw: Double,
) {
    companion object {
        val IDENTITY = QuaternionRotation(0.0, 0.0, 0.0, 1.0)

        fun fromAxisAngle(axis: Vector3D, angle: Double): QuaternionRotation {
            val normAxis = axis.normalized
            val halfAngle = 0.5 * angle
            val sinHalfAngle = sin(halfAngle)
            return QuaternionRotation(
                sinHalfAngle * normAxis.x,
                sinHalfAngle * normAxis.y,
                sinHalfAngle * normAxis.z,
                cos(halfAngle)
            )
        }
    }

    fun multiply(other: QuaternionRotation) = QuaternionRotation(
        this.qw * other.qx + this.qx * other.qw + this.qy * other.qz - this.qz * other.qy,
        this.qw * other.qy - this.qx * other.qz + this.qy * other.qw + this.qz * other.qx,
        this.qw * other.qz + this.qx * other.qy - this.qy * other.qx + this.qz * other.qw,
        this.qw * other.qw - this.qx * other.qx - this.qy * other.qy - this.qz * other.qz
    )

    operator fun times(other: QuaternionRotation) = multiply(other)

    fun apply(vector: Vector3D): Vector3D {
        val x: Double = vector.x
        val y: Double = vector.y
        val z: Double = vector.z

        val iw = -(qx * x) - qy * y - qz * z
        val ix = qw * x + qy * z - qz * y
        val iy = qw * y + qz * x - qx * z
        val iz = qw * z + qx * y - qy * x

        return Vector3D(
            iw * -qx + ix * qw + iy * -qz - iz * -qy,
            iw * -qy - ix * -qz + iy * qw + iz * -qx,
            iw * -qz + ix * -qy - iy * -qx + iz * qw
        )
    }
}