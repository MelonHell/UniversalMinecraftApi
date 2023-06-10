package ru.melonhell.uma.common.internal.core.wrappers

import net.kyori.adventure.key.Key

interface UmaItemStack : BaseWrapper, Cloneable {
    val type: Key
    val realType: Key
}