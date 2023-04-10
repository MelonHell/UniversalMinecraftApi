package ru.melonhell.uma.core.bukkit.dist

import ru.spliterash.springspigot.init.SpringSpigotPlugin

class UmaCoreBukkitPlugin : SpringSpigotPlugin() {
    override fun getAppClass() = UmaCoreBukkitApplication::class.java
}