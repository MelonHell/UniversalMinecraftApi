package ru.melonhell.uma.common.internal.camera

import ru.melonhell.uma.common.internal.core.utiltypes.EntityLocation
import ru.melonhell.uma.common.internal.core.wrappers.UmaExtension
import ru.melonhell.uma.common.internal.core.wrappers.UmaPlayer

interface CameraService {
    fun create(player: UmaPlayer, focus: CameraFocus, cameraSettings: CameraSettings, extension: UmaExtension?): Camera

    operator fun get(player: UmaPlayer): Camera?
    fun contains(player: UmaPlayer): Boolean

    fun remove(player: UmaPlayer, exitLocation: EntityLocation? = null)
    fun cameras(): List<Camera>
}