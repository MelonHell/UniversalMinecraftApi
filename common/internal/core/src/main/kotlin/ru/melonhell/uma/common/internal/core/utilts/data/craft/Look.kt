package ru.melonhell.uma.common.internal.core.utilts.data.craft

import ru.melonhell.uma.common.internal.core.utilts.data.math.QuaternionRotation
import ru.melonhell.uma.common.internal.core.utilts.data.math.Vector3D

data class Look(
    val yaw: Float = 0f,
    val pitch: Float = 0f
) {
    val rotation: QuaternionRotation by lazy { toRotation() }

    private fun toRotation(): QuaternionRotation {
        if (yaw == 0f && pitch == 0f) return QuaternionRotation.IDENTITY
        if (yaw == 0f) return pitchRotation()
        if (pitch == 0f) return yawRotation()
        return yawRotation().multiply(pitchRotation())
    }

    private fun yawRotation() = QuaternionRotation.fromAxisAngle(Vector3D(0.0, -1.0, 0.0), Math.toRadians(yaw.toDouble()))
    private fun pitchRotation() =
        QuaternionRotation.fromAxisAngle(Vector3D(1.0, 0.0, 0.0), Math.toRadians(pitch.toDouble()))
}