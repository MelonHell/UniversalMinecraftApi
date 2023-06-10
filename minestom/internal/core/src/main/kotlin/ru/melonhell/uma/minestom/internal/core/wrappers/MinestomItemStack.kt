package ru.melonhell.uma.minestom.internal.core.wrappers

import net.kyori.adventure.key.Key
import net.minestom.server.item.ItemStack
import ru.melonhell.uma.common.internal.core.wrappers.UmaItemStack

class MinestomItemStack(
    override val handle: ItemStack,
) : UmaItemStack {
    override val type: Key get() = handle.material().key()
    override val realType: Key get() = handle.material().key()

    override fun clone() = this

    companion object {
        fun ItemStack.wrap() = MinestomItemStack(this)
        fun UmaItemStack.unwrap() = handle as ItemStack
    }

    override fun hashCode() = hc()


    override fun equals(other: Any?) = eq(other)
}