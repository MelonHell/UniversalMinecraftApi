package ru.melonhell.uma.common.internal.core.services.items.providers

import ru.melonhell.uma.common.internal.core.wrappers.UmaItemStack

interface ItemProvider {
    val names: Collection<String>
    fun getItemStack(id: String, amount: Int): UmaItemStack
    fun getItemStack(id: String): UmaItemStack

    class ItemNotFoundException(provider: String, id: String) : RuntimeException("item $id not found in provider $provider")
}