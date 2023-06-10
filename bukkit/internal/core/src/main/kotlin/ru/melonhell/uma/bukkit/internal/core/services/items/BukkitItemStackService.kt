package ru.melonhell.uma.bukkit.internal.core.services.items

import org.springframework.stereotype.Component
import ru.melonhell.uma.common.internal.core.services.items.ItemStackService
import ru.melonhell.uma.common.internal.core.services.items.providers.ItemProvider
import ru.melonhell.uma.common.internal.core.wrappers.UmaItemStack

@Component
class BukkitItemStackService(
    private val providers: List<ItemProvider>
) : ItemStackService {
    override fun getItemStack(providerName: String, id: String): UmaItemStack {
        providers.forEach {
            if (it.names.contains(providerName)) return it.getItemStack(id)
        }
        throw RuntimeException("ItemProvider $providerName not found")
    }
}