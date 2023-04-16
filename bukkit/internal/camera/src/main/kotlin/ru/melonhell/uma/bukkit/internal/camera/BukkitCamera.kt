package ru.melonhell.uma.bukkit.internal.camera

import io.papermc.paper.entity.TeleportFlag
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.*
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import ru.melonhell.nmsentitylib.NmsEntityLib
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.melonhell.uma.bukkit.internal.core.utils.convert.bukkit
import ru.melonhell.uma.bukkit.internal.core.utils.convert.bukkitLocation
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitPlayer.Companion.wrap
import ru.melonhell.uma.common.internal.camera.Camera
import ru.melonhell.uma.common.internal.camera.CameraFocus
import ru.melonhell.uma.common.internal.camera.CameraService
import ru.melonhell.uma.common.internal.camera.listeners.CameraDisconnectListener
import ru.melonhell.uma.common.internal.camera.listeners.CameraInputListener
import ru.melonhell.uma.common.internal.camera.listeners.CameraListener
import ru.melonhell.uma.common.internal.camera.listeners.CameraOpenInventoryListener
import ru.melonhell.uma.common.internal.core.utilts.data.craft.EntityLocation
import ru.melonhell.uma.common.internal.core.utilts.data.craft.SteerInput
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.cos
import kotlin.math.sin

class BukkitCamera(
    private val player: Player,
    private val focus: CameraFocus,

    val plugin: Plugin,
    nmsEntityLib: NmsEntityLib,
    private val cameraService: CameraService,

    handleOpenInventory: Boolean,
    override var radius: Double,
    override var zoomEnabled: Boolean,
    override var zoomMinRadius: Double,
    override var zoomMaxRadius: Double,
) : Camera {


    private val chairEntity: Entity
    override var removed: Boolean = false
        private set


    init {
        if (handleOpenInventory) {
            chairEntity = nmsEntityLib.spawnEntity(
                focus.position.bukkitLocation(player.world),
                EntityType.HORSE,
                CreatureSpawnEvent.SpawnReason.CUSTOM,
                {
                    val horse = it as Horse
                    horse.isInvisible = true
                    horse.isInvulnerable = true
                    horse.age = -1
                    horse.ageLock = true
                    horse.isSilent = true
                    horse.maxHealth = 1.0
                    horse.addPassenger(player)
                },
                plugin
            )
        } else {
            chairEntity = nmsEntityLib.spawnEntity(
                focus.position.bukkitLocation(player.world),
                EntityType.ARMOR_STAND,
                CreatureSpawnEvent.SpawnReason.CUSTOM,
                {
                    val stand = it as ArmorStand
                    stand.isInvisible = true
                    stand.isInvulnerable = true
                    stand.isMarker = true
                    stand.isSilent = true
                    stand.maxHealth = 1.0
                    stand.addPassenger(player)
                },
                plugin
            )
        }
//        hider.hide(player.wrap())
    }

    override fun remove(exitLocation: EntityLocation?) {
        if (removed) return
        removed = true
        Bukkit.getScheduler().runTask(plugin, Runnable {
            chairEntity.remove()
            if (exitLocation != null) player.teleport(exitLocation.bukkit(player.world))
        })
        cameraService.remove(player.wrap())
//        hider.show(player.wrap())
        listeners.clear()
    }

    fun remount(teleport: Boolean = false) {
        if (teleport) player.teleport(chairEntity)
        chairEntity.addPassenger(player)
    }

    override fun tick() {
        val playerLocation = player.location
        val focusLoc = focus.position
        val rayTraceResult = player.world.rayTraceBlocks(
            focusLoc.bukkitLocation(player.world),
            playerLocation.direction.multiply(-1),
            radius
        )
        var newRadius = radius
        if (rayTraceResult != null) newRadius = rayTraceResult.hitPosition.distance(focusLoc.bukkit()) - 1
        val standLocation =
            getStandLocation(
                focusLoc.bukkitLocation(player.world),
                playerLocation.yaw.toDouble(),
                playerLocation.pitch.toDouble(),
                newRadius
            )

        @Suppress("UnstableApiUsage")
        chairEntity.teleport(
            standLocation,
            TeleportFlag.EntityState.RETAIN_VEHICLE,
            TeleportFlag.EntityState.RETAIN_PASSENGERS
        )

        Bukkit.getScheduler().runTask(plugin, Runnable { if (!removed) addEffects() })
    }

    private fun addEffects() {
        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 10, false, false))
        player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 20, 10, false, false))
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 10, false, false))
    }


    private fun getStandLocation(center: Location, yaw: Double, pitch: Double, radius: Double): Location {
        return center.clone().add(getStandOffset(yaw, pitch, radius)).subtract(
            0.0,
            (chairEntity as NelEntityBukkit).passengersPlayerEyesOffset,
            0.0
        )
    }

    private fun getStandOffset(yaw: Double, pitch: Double, radius: Double): Vector {
        val yawRadians = Math.toRadians(yaw)
        val yawSin = sin(yawRadians)
        val yawCos = cos(yawRadians)
        val pitchRadians = Math.toRadians(pitch)
        val pitchSin = sin(pitchRadians)
        val pitchCos = cos(pitchRadians)
        val x = yawSin * radius * pitchCos
        val z = yawCos * radius * pitchCos * -1
        val y = pitchSin * radius
        return Vector(x, y, z)
    }

    // Event System

    private val listeners = CopyOnWriteArrayList<CameraListener>()

    override fun addListener(listener: CameraListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: CameraListener) {
        listeners.remove(listener)
    }

    internal fun postInputEvent(input: SteerInput) {
        listeners.filterIsInstance<CameraInputListener>().forEach { it.onInput(input) }
    }

    internal fun postDisconnectEvent() {
        listeners.filterIsInstance<CameraDisconnectListener>().forEach { it.onDisconnect() }
    }

    internal fun postOpenInventoryEvent() {
        listeners.filterIsInstance<CameraOpenInventoryListener>().forEach { it.onOpenInventory() }
    }
}