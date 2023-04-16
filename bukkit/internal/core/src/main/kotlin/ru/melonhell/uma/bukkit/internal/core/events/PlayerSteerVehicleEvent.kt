package ru.melonhell.uma.bukkit.internal.core.events

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent
import ru.melonhell.uma.common.internal.core.utilts.data.craft.SteerInput

class PlayerSteerVehicleEvent(
    player: Player,
    val input: SteerInput
) : PlayerEvent(player, true) {
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        @JvmStatic
        val handlerList = HandlerList()
    }
}