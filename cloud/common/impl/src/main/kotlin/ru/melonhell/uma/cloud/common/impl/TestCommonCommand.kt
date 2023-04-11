package ru.melonhell.uma.cloud.common.impl

import cloud.commandframework.annotations.CommandMethod
import ru.melonhell.uma.cloud.common.api.CloudCommand
import ru.melonhell.uma.core.common.api.wrappers.UmaCommandSender

@CloudCommand
class TestCommonCommand {
    @CommandMethod("uma test common")
    fun test(
        sender: UmaCommandSender
    ) {
        sender.sendMessage("UMA COMMON!")
    }
}