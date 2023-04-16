package ru.melonhell.uma.common.internal.core.utilts.math

import ru.melonhell.uma.common.internal.core.utilts.data.math.Vector3D

typealias ApacheVector3D = org.apache.commons.geometry.euclidean.threed.Vector3D

fun Vector3D.toApache(): ApacheVector3D = ApacheVector3D.of(x, y, z)
fun ApacheVector3D.toUma(): Vector3D = Vector3D(x, y, z)