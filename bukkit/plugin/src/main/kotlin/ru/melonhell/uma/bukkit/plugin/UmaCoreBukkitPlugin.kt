package ru.melonhell.uma.bukkit.plugin

import ru.spliterash.springspigot.init.SpringSpigotPlugin

class UmaCoreBukkitPlugin : SpringSpigotPlugin() {
    override fun getAppClass() = UmaCoreBukkitApplication::class.java
}