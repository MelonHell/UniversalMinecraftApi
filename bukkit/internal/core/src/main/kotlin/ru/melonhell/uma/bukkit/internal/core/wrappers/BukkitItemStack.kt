package ru.melonhell.uma.bukkit.internal.core.wrappers

import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import ru.melonhell.uma.common.internal.core.wrappers.UmaItemStack

class BukkitItemStack(
    override val handle: ItemStack,
) : UmaItemStack {

    override val type: Key get() = handle.type.key
    override val realType: Key get() = handle.type.key

    override fun clone(): BukkitItemStack = BukkitItemStack(handle.clone())

    companion object {
        fun ItemStack.wrap() = BukkitItemStack(this)
        fun UmaItemStack.unwrap() = handle as ItemStack
    }

    override fun hashCode() = hc()


    override fun equals(other: Any?) = eq(other)
}