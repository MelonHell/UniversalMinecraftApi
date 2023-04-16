package ru.melonhell.uma.minestom.internal.core.wrappers

import net.kyori.adventure.audience.Audience
import net.minestom.server.entity.Player
import ru.melonhell.uma.common.internal.core.utilts.data.craft.EntityLocation
import ru.melonhell.uma.common.internal.core.wrappers.UmaPlayer
import ru.melonhell.uma.common.internal.core.wrappers.UmaWorld
import ru.melonhell.uma.minestom.internal.core.utils.convert.uma
import ru.melonhell.uma.minestom.internal.core.wrappers.MinestomWorld.Companion.wrap
import java.util.*

class MinestomPlayer(
    override val handle: Player,
) : MinestomCommandSender(handle), UmaPlayer, Audience by handle {
    override val name: String get() = handle.username
    override val uuid: UUID get() = handle.uuid
    override val world: UmaWorld?
        get() = handle.instance?.wrap()
    override val location: EntityLocation
        get() = handle.position.uma()

    companion object {
        fun Player.wrap() = MinestomPlayer(this)
        fun UmaPlayer.unwrap() = handle as Player
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}