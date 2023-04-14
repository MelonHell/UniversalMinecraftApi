package ru.melonhell.uma.core.minestom.api.wrappers

import net.minestom.server.extensions.Extension
import ru.melonhell.uma.core.common.api.wrappers.UmaPlugin

class MinestomPlugin(override val handle: Extension) : UmaPlugin {
    override val name get() = handle.origin.name
    override val dataDir get() = handle.dataDirectory

    companion object {
        fun Extension.wrap() = MinestomPlugin(this)
        fun UmaPlugin.unwrap() = handle as Extension
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}