package ru.melonhell.uma.bukkit.internal.core.wrappers

import net.kyori.adventure.audience.Audience
import org.bukkit.entity.Player
import ru.melonhell.uma.bukkit.internal.core.utils.convert.uma
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitWorld.Companion.wrap
import ru.melonhell.uma.common.internal.core.utilts.data.craft.EntityLocation
import ru.melonhell.uma.common.internal.core.wrappers.UmaPlayer
import ru.melonhell.uma.common.internal.core.wrappers.UmaWorld
import java.util.*

class BukkitPlayer(
    override val handle: Player,
) : BukkitCommandSender(handle), UmaPlayer, Audience by handle {
    override val name: String get() = handle.name
    override val uuid: UUID get() = handle.uniqueId
    override val world: UmaWorld
        get() = handle.world.wrap()
    override val location: EntityLocation
        get() = handle.location.uma()

    companion object {
        fun Player.wrap() = BukkitPlayer(this)
        fun UmaPlayer.unwrap() = handle as Player
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}