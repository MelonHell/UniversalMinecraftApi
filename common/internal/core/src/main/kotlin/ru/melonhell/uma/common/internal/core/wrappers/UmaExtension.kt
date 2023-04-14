package ru.melonhell.uma.common.internal.core.wrappers

import java.nio.file.Path

interface UmaExtension : BaseWrapper {
    val name: String
    val dataDir: Path
}