package ru.melonhell.uma.bukkit.internal.core.events

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

class PlayerOpenVehicleInventoryEvent(
    player: Player
) : PlayerEvent(player, true) {
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        @JvmStatic
        val handlerList = HandlerList()
    }
}