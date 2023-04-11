package ru.melonhell.uma.core.common.api.wrappers

import net.kyori.adventure.audience.Audience
import java.util.*

interface UmaPlayer : UmaCommandSender, Audience {
    val name: String
    val uuid: UUID
}