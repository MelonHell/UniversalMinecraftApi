package ru.melonhell.uma.bukkit.internal.camera

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.*
import org.bukkit.event.server.PluginDisableEvent
import org.spigotmc.event.entity.EntityDismountEvent
import ru.melonhell.uma.bukkit.internal.core.events.PlayerOpenVehicleInventoryEvent
import ru.melonhell.uma.bukkit.internal.core.events.PlayerSteerVehicleEvent
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitPlayer.Companion.wrap
import ru.melonhell.uma.common.internal.camera.CameraService
import ru.spliterash.springspigot.listener.SpigotListener

@SpigotListener
class BukkitCameraListener(
    private val cameraService: CameraService,
) : Listener {
    private fun get(player: Player): BukkitCamera? = cameraService[player.wrap()] as BukkitCamera?
    private fun contains(player: Player): Boolean = cameraService.contains(player.wrap())

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val camera = get(event.player) ?: return
        camera.postDisconnectEvent()
        camera.remove()
    }

    @EventHandler(ignoreCancelled = true)
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (contains(event.player)) event.isCancelled = true
    }

    @EventHandler(ignoreCancelled = true)
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        if (contains(event.player)) event.isCancelled = true
    }

    @EventHandler(ignoreCancelled = true)
    fun onPlayerDismount(event: EntityDismountEvent) {
        val entity = event.entity
        if (entity !is Player) return
        get(entity)?.let { event.isCancelled = true }
    }

    @EventHandler
    fun onChangeWorld(event: PlayerChangedWorldEvent) {
        get(event.player)?.remount(true)
    }

    @EventHandler(ignoreCancelled = true)
    fun onPlayerMove(event: PlayerMoveEvent) {
        get(event.player)?.let {
            event.isCancelled = true
            it.remount(true)
        }
    }

    @EventHandler
    fun onInput(event: PlayerSteerVehicleEvent) {
        get(event.player)?.postInputEvent(event.input)
    }

    @EventHandler
    fun onScroll(event: PlayerItemHeldEvent) {
        val camera = get(event.player) ?: return
        if (!camera.zoomEnabled) return
        val oldSlot = event.previousSlot
        val newSlot = event.newSlot
        var addSlot = newSlot - oldSlot
        if (addSlot > 4) addSlot -= 9 else if (addSlot < -4) addSlot += 9
        camera.radius = (camera.radius + addSlot).coerceIn(camera.zoomMinRadius, camera.zoomMaxRadius)
    }

    @EventHandler
    fun onOpenVehicleInventory(event: PlayerOpenVehicleInventoryEvent) {
        val camera = get(event.player) ?: return
        camera.postOpenInventoryEvent()
    }

    @EventHandler
    fun onPluginDisable(event: PluginDisableEvent) {
        val cameras = cameraService.cameras()
        cameras.forEach {
            it as BukkitCamera
            if (it.plugin == event.plugin) it.remove()
        }
    }
}