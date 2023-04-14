package ru.melonhell.uma.common.internal.core.wrappers

import net.kyori.adventure.audience.Audience
import java.util.*

interface UmaPlayer : UmaCommandSender, Audience {
    val name: String
    val uuid: UUID
}