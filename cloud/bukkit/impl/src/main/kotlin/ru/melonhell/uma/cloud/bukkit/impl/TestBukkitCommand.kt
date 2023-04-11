package ru.melonhell.uma.cloud.bukkit.impl

import cloud.commandframework.annotations.CommandMethod
import ru.melonhell.uma.cloud.common.api.CloudCommand
import ru.melonhell.uma.core.common.api.wrappers.UmaCommandSender

@CloudCommand
class TestBukkitCommand {
    @CommandMethod("uma test bukkit")
    fun test(
        sender: UmaCommandSender
    ) {
        sender.sendMessage("UMA BUKKIT!")
    }
}