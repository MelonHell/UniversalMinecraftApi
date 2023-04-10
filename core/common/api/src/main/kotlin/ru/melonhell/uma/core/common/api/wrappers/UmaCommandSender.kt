package ru.melonhell.uma.core.common.api.wrappers

interface UmaCommandSender : BaseWrapper {
    fun sendMessage(message: String)
}