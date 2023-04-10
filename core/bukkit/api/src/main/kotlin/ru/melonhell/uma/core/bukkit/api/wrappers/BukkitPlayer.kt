package ru.melonhell.uma.core.bukkit.api.wrappers

import org.bukkit.entity.Player
import ru.melonhell.uma.core.common.api.wrappers.UmaPlayer
import java.util.UUID

class BukkitPlayer(override val handle: Player) : BukkitCommandSender(handle), UmaPlayer {
    override val name: String get() = handle.name
    override val uuid: UUID get() = handle.uniqueId

    companion object {
        fun Player.wrap() = BukkitPlayer(this)
        fun UmaPlayer.unwrap() = handle as Player
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}