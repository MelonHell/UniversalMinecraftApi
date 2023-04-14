package ru.melonhell.uma.common.internal.cloud

import ru.melonhell.uma.common.internal.core.wrappers.UmaExtension


interface CloudCommandRegistrar {
    fun registerCommand(obj: Any, plugin: UmaExtension? = null)
}