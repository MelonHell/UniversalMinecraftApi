package ru.melonhell.uma.bukkit.internal.core.wrappers

import org.bukkit.World
import ru.melonhell.uma.common.internal.core.wrappers.UmaWorld

class BukkitWorld(override val handle: World) : UmaWorld {
    companion object {
        fun World.wrap() = BukkitWorld(this)
        fun UmaWorld.unwrap() = handle as World
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}