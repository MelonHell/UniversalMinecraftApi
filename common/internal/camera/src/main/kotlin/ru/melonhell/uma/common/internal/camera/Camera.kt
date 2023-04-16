package ru.melonhell.uma.common.internal.camera

import ru.melonhell.uma.common.internal.camera.listeners.CameraListener
import ru.melonhell.uma.common.internal.core.utiltypes.EntityLocation

interface Camera {
    var radius: Double
    var zoomEnabled: Boolean
    var zoomMinRadius: Double
    var zoomMaxRadius: Double

    fun tick()

    val removed: Boolean
    fun remove(exitLocation: EntityLocation? = null)

    fun addListener(listener: CameraListener)
    fun removeListener(listener: CameraListener)
}