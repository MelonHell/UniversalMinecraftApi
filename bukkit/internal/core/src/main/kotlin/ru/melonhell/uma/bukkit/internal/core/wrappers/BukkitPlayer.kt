package ru.melonhell.uma.bukkit.internal.core.wrappers

import net.kyori.adventure.audience.Audience
import org.bukkit.entity.Player
import ru.melonhell.uma.common.internal.core.wrappers.UmaPlayer
import java.util.*

class BukkitPlayer(
    override val handle: Player,
) : BukkitCommandSender(handle), UmaPlayer, Audience by handle {
    override val name: String get() = handle.name
    override val uuid: UUID get() = handle.uniqueId

    companion object {
        fun Player.wrap() = BukkitPlayer(this)
        fun UmaPlayer.unwrap() = handle as Player
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}