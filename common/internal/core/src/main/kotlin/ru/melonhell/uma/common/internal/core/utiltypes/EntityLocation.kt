package ru.melonhell.uma.common.internal.core.utiltypes

data class EntityLocation(
    val position: Vector3D,
    val look: Look = Look(),
) {
    val x: Double get() = position.x
    val y: Double get() = position.y
    val z: Double get() = position.z

    val yaw: Float get() = look.yaw
    val pitch: Float get() = look.pitch
}