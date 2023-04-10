package ru.melonhell.uma.cloud.bukkit.dist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import ru.melonhell.uma.cloud.bukkit.api.UmaCloudBukkitConfiguration

@SpringBootApplication(scanBasePackages = ["ru.melonhell.uma.cloud.bukkit.dist"])
@Import(UmaCloudBukkitConfiguration::class)
class UmaCloudBukkitApplication