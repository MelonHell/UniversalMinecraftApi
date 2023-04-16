package ru.melonhell.uma.common.internal.core.utilts.math

import ru.melonhell.uma.common.internal.core.utilts.data.math.QuaternionRotation


typealias ApacheQuaternionRotation = org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation

fun QuaternionRotation.toApache(): ApacheQuaternionRotation = ApacheQuaternionRotation.of(qw, qx, qy, qz)

fun ApacheQuaternionRotation.toUma(): QuaternionRotation = QuaternionRotation(quaternion.x, quaternion.y, quaternion.z, quaternion.w)