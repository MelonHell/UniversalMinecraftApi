package ru.melonhell.uma.minestom.internal.core.utils.convert

import net.minestom.server.coordinate.Pos
import ru.melonhell.uma.common.internal.core.utiltypes.EntityLocation

fun Pos.uma() = EntityLocation(x, y, z, yaw, pitch)