package ru.melonhell.uma.common.internal.core.wrappers

import net.kyori.adventure.audience.Audience

interface UmaCommandSender : BaseWrapper, Audience {
    fun sendMessage(message: String)
}