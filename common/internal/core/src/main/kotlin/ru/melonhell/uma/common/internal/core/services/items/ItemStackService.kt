package ru.melonhell.uma.common.internal.core.services.items

import ru.melonhell.uma.common.internal.core.wrappers.UmaItemStack

interface ItemStackService {
    fun getItemStack(providerName: String, id: String): UmaItemStack
}