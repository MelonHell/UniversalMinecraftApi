package ru.melonhell.uma.cloud.bukkit.api.external

import cloud.commandframework.paper.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin
import ru.melonhell.uma.core.common.api.wrappers.UmaCommandSender

interface BukkitCloudCommandRegister {
    fun manager(plugin: JavaPlugin): PaperCommandManager<UmaCommandSender>
    fun registerCommand(plugin: JavaPlugin, obj: Any)
}