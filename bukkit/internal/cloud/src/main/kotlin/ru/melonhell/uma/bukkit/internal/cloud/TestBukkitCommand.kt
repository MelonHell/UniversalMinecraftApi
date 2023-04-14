package ru.melonhell.uma.bukkit.internal.cloud

import cloud.commandframework.annotations.CommandMethod
import ru.melonhell.uma.common.internal.cloud.CloudCommand
import ru.melonhell.uma.common.internal.core.wrappers.UmaCommandSender

@CloudCommand
class TestBukkitCommand {
    @CommandMethod("uma test bukkit")
    fun test(
        sender: UmaCommandSender,
    ) {
        sender.sendMessage("UMA BUKKIT!")
    }
}