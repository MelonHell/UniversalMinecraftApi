package ru.melonhell.uma.core.minestom.api.wrappers

import net.kyori.adventure.audience.Audience
import net.minestom.server.entity.Player
import ru.melonhell.uma.core.common.api.wrappers.UmaPlayer
import java.util.*

class MinestomPlayer(
    override val handle: Player
) : MinestomCommandSender(handle), UmaPlayer, Audience by handle {
    override val name: String get() = handle.username
    override val uuid: UUID get() = handle.uuid

    companion object {
        fun Player.wrap() = MinestomPlayer(this)
        fun UmaPlayer.unwrap() = handle as Player
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}