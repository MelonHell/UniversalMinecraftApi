package ru.melonhell.uma.bukkit.internal.core.services.items.providers

import org.springframework.stereotype.Component
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitItemStack
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitItemStack.Companion.wrap
import ru.melonhell.uma.common.internal.core.services.items.providers.ItemProvider
import xyz.xenondevs.nova.api.Nova

@Component
class NovaItemProvider : ItemProvider {
    override val names = listOf("Nova")

    override fun getItemStack(id: String, amount: Int): BukkitItemStack {
        try {
            return Nova.getNova().itemRegistry.get(id).createItemStack(amount).wrap()
        } catch (ex: IllegalArgumentException) {
            throw ItemProvider.ItemNotFoundException(names.first(), id)
        }
    }

    override fun getItemStack(id: String): BukkitItemStack {
        try {
            return Nova.getNova().itemRegistry.get(id).createItemStack().wrap()
        } catch (ex: IllegalArgumentException) {
            throw ItemProvider.ItemNotFoundException(names.first(), id)
        }
    }
}