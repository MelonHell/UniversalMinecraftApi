package ru.melonhell.uma.core.common.api.wrappers

import java.util.*

interface UmaPlayer : UmaCommandSender {
    val name: String
    val uuid: UUID
}