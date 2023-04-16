package ru.melonhell.uma.common.internal.camera.listeners

import ru.melonhell.uma.common.internal.core.utiltypes.SteerInput

interface CameraInputListener : CameraListener {
    fun onInput(input: SteerInput)
}