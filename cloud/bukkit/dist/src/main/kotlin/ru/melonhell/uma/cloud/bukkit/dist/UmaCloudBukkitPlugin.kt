package ru.melonhell.uma.cloud.bukkit.dist

import ru.spliterash.springspigot.init.SpringSpigotPlugin

class UmaCloudBukkitPlugin : SpringSpigotPlugin() {
    override fun getAppClass() = UmaCloudBukkitApplication::class.java
}