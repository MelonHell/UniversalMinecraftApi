package ru.melonhell.uma.core.common.api.wrappers

import java.nio.file.Path

interface UmaPlugin : BaseWrapper {
    val name: String
    val dataDir: Path
}