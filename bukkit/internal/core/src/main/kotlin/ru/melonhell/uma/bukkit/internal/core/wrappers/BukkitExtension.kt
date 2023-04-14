package ru.melonhell.uma.bukkit.internal.core.wrappers

import org.bukkit.plugin.Plugin
import ru.melonhell.uma.common.internal.core.wrappers.UmaExtension
import java.nio.file.Path

class BukkitExtension(override val handle: Plugin) : UmaExtension {
    override val name: String get() = handle.name
    override val dataDir: Path get() = handle.dataFolder.toPath()

    companion object {
        fun Plugin.wrap() = BukkitExtension(this)
        fun UmaExtension.unwrap() = handle as Plugin
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}