package ru.melonhell.uma.bukkit.internal.core.utils.convert

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import ru.melonhell.uma.common.internal.core.utilts.data.craft.EntityLocation
import ru.melonhell.uma.common.internal.core.utilts.data.math.Vector3D

fun Vector3D.bukkitLocation(world: World?) = Location(world, x, y, z)
fun Vector3D.bukkit() = Vector(x, y, z)

fun EntityLocation.bukkit(world: World?) = Location(world, x, y, z, yaw, pitch)

fun Location.uma() = EntityLocation(x, y, z, yaw, pitch)