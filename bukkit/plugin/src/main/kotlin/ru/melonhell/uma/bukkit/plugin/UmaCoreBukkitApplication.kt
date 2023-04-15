package ru.melonhell.uma.bukkit.plugin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import ru.melonhell.uma.bukkit.internal.core.UmaBukkitInternalConfiguration

@SpringBootApplication
@Import(UmaBukkitInternalConfiguration::class)
class UmaCoreBukkitApplication