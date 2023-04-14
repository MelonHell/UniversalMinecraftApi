package ru.melonhell.uma.core.bukkit.api.wrappers

import org.bukkit.plugin.Plugin
import ru.melonhell.uma.core.common.api.wrappers.UmaPlugin
import java.nio.file.Path

class BukkitPlugin(override val handle: Plugin) : UmaPlugin {
    override val name: String get() = handle.name
    override val dataDir: Path get() = handle.dataFolder.toPath()

    companion object {
        fun Plugin.wrap() = BukkitPlugin(this)
        fun UmaPlugin.unwrap() = handle as Plugin
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}