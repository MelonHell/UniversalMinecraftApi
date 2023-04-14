package ru.melonhell.uma.minestom.internal.core.wrappers

import net.minestom.server.instance.Instance
import ru.melonhell.uma.common.internal.core.wrappers.UmaWorld

class MinestomWorld(override val handle: Instance) : UmaWorld {
    companion object {
        fun Instance.wrap() = MinestomWorld(this)
        fun UmaWorld.unwrap() = handle as Instance
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}