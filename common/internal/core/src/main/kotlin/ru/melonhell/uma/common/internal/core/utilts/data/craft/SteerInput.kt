package ru.melonhell.uma.common.internal.core.utilts.data.craft

data class SteerInput(
    val isForward: Boolean = false,
    val isBackward: Boolean = false,
    val isLeft: Boolean = false,
    val isRight: Boolean = false,
    val isJump: Boolean = false,
    val isSneak: Boolean = false
)