package ru.melonhell.uma.minestom.internal.core.wrappers

import net.kyori.adventure.audience.Audience
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import ru.melonhell.uma.common.internal.core.wrappers.UmaCommandSender

open class MinestomCommandSender(
    override val handle: CommandSender,
) : UmaCommandSender, Audience by handle {
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