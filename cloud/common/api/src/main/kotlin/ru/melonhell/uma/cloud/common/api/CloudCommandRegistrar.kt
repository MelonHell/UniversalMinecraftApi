package ru.melonhell.uma.cloud.common.api

import ru.melonhell.uma.core.common.api.wrappers.UmaPlugin

interface CloudCommandRegistrar {
    fun registerCommand(obj: Any, plugin: UmaPlugin? = null)
}