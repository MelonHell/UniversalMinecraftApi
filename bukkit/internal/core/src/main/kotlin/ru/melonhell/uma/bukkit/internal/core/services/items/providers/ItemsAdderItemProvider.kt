package ru.melonhell.uma.bukkit.internal.core.services.items.providers

import dev.lone.itemsadder.api.CustomStack
import org.springframework.stereotype.Component
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitItemStack.Companion.wrap
import ru.melonhell.uma.common.internal.core.services.items.providers.ItemProvider

@Component
class ItemsAdderItemProvider : ItemProvider {
    override val names = listOf("ItemsAdder", "IA")

    override fun getItemStack(id: String, amount: Int) =
        CustomStack.getInstance(id)?.itemStack?.apply { setAmount(amount) }?.wrap()
            ?: throw ItemProvider.ItemNotFoundException(names.first(), id)

    override fun getItemStack(id: String) =
        CustomStack.getInstance(id)?.itemStack?.wrap()
            ?: throw ItemProvider.ItemNotFoundException(names.first(), id)
}