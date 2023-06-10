package ru.melonhell.uma.bukkit.internal.core.services.items.providers

import net.kyori.adventure.key.Key
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitItemStack
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitItemStack.Companion.wrap
import ru.melonhell.uma.common.internal.core.services.items.providers.ItemProvider

@Component
class VanillaItemProvider : ItemProvider {
    override val names = listOf("Minecraft")

    override fun getItemStack(id: String, amount: Int): BukkitItemStack {
        val material = Material.valueOf(Key.key(id).value().uppercase())
        return ItemStack(material, amount).wrap()
    }

    override fun getItemStack(id: String) = getItemStack(id, 1)
}