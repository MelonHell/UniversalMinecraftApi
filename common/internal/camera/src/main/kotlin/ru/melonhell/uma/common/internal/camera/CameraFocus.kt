package ru.melonhell.uma.common.internal.camera

import ru.melonhell.uma.common.internal.core.utilts.data.math.Vector3D
import ru.melonhell.uma.common.internal.core.wrappers.UmaWorld

interface CameraFocus {
    val world: UmaWorld
    val position: Vector3D
}