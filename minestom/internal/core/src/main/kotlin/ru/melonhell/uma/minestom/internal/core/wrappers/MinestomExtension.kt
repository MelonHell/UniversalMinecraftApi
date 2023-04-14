package ru.melonhell.uma.minestom.internal.core.wrappers

import net.minestom.server.extensions.Extension
import ru.melonhell.uma.common.internal.core.wrappers.UmaExtension

class MinestomExtension(override val handle: Extension) : UmaExtension {
    override val name get() = handle.origin.name
    override val dataDir get() = handle.dataDirectory

    companion object {
        fun Extension.wrap() = MinestomExtension(this)
        fun UmaExtension.unwrap() = handle as Extension
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}