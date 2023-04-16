package ru.melonhell.uma.bukkit.internal.camera

import org.bukkit.plugin.Plugin
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.NmsEntityLib
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitExtension.Companion.unwrap
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitPlayer.Companion.unwrap
import ru.melonhell.uma.common.internal.camera.Camera
import ru.melonhell.uma.common.internal.camera.CameraFocus
import ru.melonhell.uma.common.internal.camera.CameraService
import ru.melonhell.uma.common.internal.camera.CameraSettings
import ru.melonhell.uma.common.internal.core.utilts.data.craft.EntityLocation
import ru.melonhell.uma.common.internal.core.wrappers.UmaExtension
import ru.melonhell.uma.common.internal.core.wrappers.UmaPlayer

@Component
class BukkitCameraService(
    private val nmsEntityLib: NmsEntityLib,
    private val globalPlugin: Plugin
) : CameraService {

    private val cameras: MutableMap<UmaPlayer, Camera> = HashMap()

    override fun create(
        player: UmaPlayer,
        focus: CameraFocus,
        cameraSettings: CameraSettings,
        extension: UmaExtension?,
    ): Camera {
        if (cameras.containsKey(player)) throw IllegalStateException()
        return BukkitCamera(
            player.unwrap(),
            focus,
            extension?.unwrap() ?: globalPlugin,
            nmsEntityLib,
            this,
            cameraSettings.handleOpenInventory,
            cameraSettings.radius,
            cameraSettings.zoomEnabled,
            cameraSettings.zoomMinRadius,
            cameraSettings.zoomMaxRadius
        ).apply {
            cameras[player] = this
        }
    }

    override fun get(player: UmaPlayer) = cameras[player]

    override fun contains(player: UmaPlayer) = cameras.containsKey(player)

    override fun remove(player: UmaPlayer, exitLocation: EntityLocation?) {
        val camera = cameras[player] ?: return
        cameras.remove(player)
        if (!camera.removed) camera.remove(exitLocation)
    }


    override fun cameras(): List<Camera> {
        return cameras.values.toList()
    }
}