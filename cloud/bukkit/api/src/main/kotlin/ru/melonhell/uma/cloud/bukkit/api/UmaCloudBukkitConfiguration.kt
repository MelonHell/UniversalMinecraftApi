package ru.melonhell.uma.cloud.bukkit.api

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import ru.melonhell.uma.cloud.common.api.UmaCloudCommonConfiguration

@Configuration
@ComponentScan(basePackages = ["ru.melonhell.uma.cloud.bukkit"])
@Import(UmaCloudCommonConfiguration::class)
class UmaCloudBukkitConfiguration