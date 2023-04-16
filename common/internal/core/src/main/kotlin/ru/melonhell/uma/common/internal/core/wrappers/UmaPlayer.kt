package ru.melonhell.uma.common.internal.core.wrappers

import net.kyori.adventure.audience.Audience
import ru.melonhell.uma.common.internal.core.utilts.data.craft.EntityLocation
import java.util.*

interface UmaPlayer : UmaCommandSender, Audience {
    val name: String
    val uuid: UUID
    val world: UmaWorld?
    val location: EntityLocation
}