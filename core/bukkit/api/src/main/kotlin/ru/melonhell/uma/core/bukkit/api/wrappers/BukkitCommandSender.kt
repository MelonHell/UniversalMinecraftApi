package ru.melonhell.uma.core.bukkit.api.wrappers

import net.kyori.adventure.audience.Audience
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.melonhell.uma.core.common.api.wrappers.UmaCommandSender

open class BukkitCommandSender(
    override val handle: CommandSender,
) : UmaCommandSender, Audience by handle {
    override fun sendMessage(message: String) = handle.sendMessage(message)

    companion object {
        fun CommandSender.wrap(): BukkitCommandSender {
            if (this is Player) return BukkitPlayer(this)
            return BukkitCommandSender(this)
        }

        fun UmaCommandSender.unwrap() = handle as CommandSender
    }

    override fun hashCode() = hc()
    override fun equals(other: Any?) = eq(other)
}