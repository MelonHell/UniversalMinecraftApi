package ru.melonhell.uma.bukkit.internal.core.events.impl

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import com.comphenix.protocol.wrappers.EnumWrappers
import jakarta.annotation.PostConstruct
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component
import ru.melonhell.uma.bukkit.internal.core.events.PlayerOpenVehicleInventoryEvent
import ru.melonhell.uma.bukkit.internal.core.events.PlayerSteerVehicleEvent
import ru.melonhell.uma.common.internal.core.utiltypes.SteerInput

@Component
class PacketListener(
    plugin: JavaPlugin,
) : PacketAdapter(
    plugin,
    ListenerPriority.LOWEST,
    listOf(
        PacketType.Play.Client.STEER_VEHICLE,
        PacketType.Play.Client.ENTITY_ACTION
    )
) {
    @PostConstruct
    fun init() {
        ProtocolLibrary.getProtocolManager().addPacketListener(this)
    }

    override fun onPacketReceiving(event: PacketEvent) {
        when (event.packetType) {
            PacketType.Play.Client.STEER_VEHICLE -> onSteerVehicle(event)
            PacketType.Play.Client.ENTITY_ACTION -> onEntityAction(event)
        }
    }

    private fun onSteerVehicle(event: PacketEvent) {
        val packet = event.packet
        val sideways = packet.float.readSafely(0)
        val forward = packet.float.readSafely(1)
        val jump = packet.booleans.readSafely(0)
        val unmount = packet.booleans.readSafely(1)
        val inputData = SteerInput(forward > 0, forward < 0, sideways > 0, sideways < 0, jump, unmount)
        Bukkit.getPluginManager().callEvent(PlayerSteerVehicleEvent(event.player, inputData))
    }

    private fun onEntityAction(event: PacketEvent) {
        val action = event.packet.playerActions.readSafely(0)
        if (action != EnumWrappers.PlayerAction.OPEN_INVENTORY) return
        Bukkit.getPluginManager().callEvent(PlayerOpenVehicleInventoryEvent(event.player))
    }
}