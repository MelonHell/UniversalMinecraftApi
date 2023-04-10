package ru.melonhell.uma.core.bukkit.dist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import ru.melonhell.uma.core.bukkit.api.UmaCoreBukkitConfiguration

@SpringBootApplication(scanBasePackages = ["ru.melonhell.uma.core.bukkit.dist"])
@Import(UmaCoreBukkitConfiguration::class)
class UmaCoreBukkitApplication