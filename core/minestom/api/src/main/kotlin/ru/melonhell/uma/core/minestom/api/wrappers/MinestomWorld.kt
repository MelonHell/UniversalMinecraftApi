package ru.melonhell.uma.core.minestom.api.wrappers

import net.minestom.server.instance.Instance
import ru.melonhell.uma.core.common.api.wrappers.UmaWorld

class MinestomWorld(override val handle: Instance) : UmaWorld {
    companion object {
        fun Instance.wrap() = MinestomWorld(this)
        fun UmaWorld.unwrap() = handle as Instance
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}