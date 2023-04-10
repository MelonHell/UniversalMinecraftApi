package ru.melonhell.uma.core.bukkit.api.wrappers

import org.bukkit.World
import ru.melonhell.uma.core.common.api.wrappers.UmaWorld

class BukkitWorld(override val handle: World) : UmaWorld {
    companion object {
        fun World.wrap() = BukkitWorld(this)
        fun UmaWorld.unwrap() = handle as World
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}