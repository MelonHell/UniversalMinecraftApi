package ru.melonhell.uma.core.minestom.api.wrappers

import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import ru.melonhell.uma.core.common.api.wrappers.UmaCommandSender

open class MinestomCommandSender(override val handle: CommandSender) : UmaCommandSender {
    override fun sendMessage(message: String) = handle.sendMessage(message)

    companion object {
        fun CommandSender.wrap(): MinestomCommandSender {
            if (this is Player) return MinestomPlayer(this)
            return MinestomCommandSender(this)
        }

        fun UmaCommandSender.unwrap() = handle as CommandSender
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}