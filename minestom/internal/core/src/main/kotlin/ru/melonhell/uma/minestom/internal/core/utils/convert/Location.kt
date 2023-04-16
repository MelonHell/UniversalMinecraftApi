package ru.melonhell.uma.minestom.internal.core.utils.convert

import net.minestom.server.coordinate.Pos
import ru.melonhell.uma.common.internal.core.utilts.data.craft.EntityLocation

fun Pos.uma() = EntityLocation(x, y, z, yaw, pitch)