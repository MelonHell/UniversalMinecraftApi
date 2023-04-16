package ru.melonhell.uma.common.internal.camera

data class CameraSettings(
    var autoTick: Boolean = true,
    var handleOpenInventory: Boolean = false,
    var radius: Double = 5.0,
    var zoomEnabled: Boolean = false,
    var zoomMinRadius: Double = 1.0,
    var zoomMaxRadius: Double = 10.0
)