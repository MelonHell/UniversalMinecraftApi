package ru.melonhell.uma.core.common.api.wrappers

import net.kyori.adventure.audience.Audience

interface UmaCommandSender : BaseWrapper, Audience {
    fun sendMessage(message: String)
}