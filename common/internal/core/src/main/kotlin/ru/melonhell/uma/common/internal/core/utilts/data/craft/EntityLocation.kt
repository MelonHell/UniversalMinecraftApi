package ru.melonhell.uma.common.internal.core.utilts.data.craft

import ru.melonhell.uma.common.internal.core.utilts.data.math.Transform
import ru.melonhell.uma.common.internal.core.utilts.data.math.Vector3D

data class EntityLocation(
    val position: Vector3D,
    val look: Look = Look(),
) {
    constructor(x: Double, y: Double, z: Double, yaw: Float = 0f, pitch: Float = 0f) : this(
        Vector3D(x, y, z),
        Look(yaw, pitch)
    )

    val x: Double get() = position.x
    val y: Double get() = position.y
    val z: Double get() = position.z

    val yaw: Float get() = look.yaw
    val pitch: Float get() = look.pitch

    val transform by lazy { Transform(position, look.rotation) }
    val transformWithNoPitch by lazy { Transform(position, Look(look.yaw, 0f).rotation) }
}