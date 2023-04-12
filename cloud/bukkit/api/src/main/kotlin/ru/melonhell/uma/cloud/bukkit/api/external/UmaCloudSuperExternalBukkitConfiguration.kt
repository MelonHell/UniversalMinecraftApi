package ru.melonhell.uma.cloud.bukkit.api.external

import org.springframework.context.annotation.Configuration
import ru.melonhell.uma.core.common.api.annotation.ExternalContextOnly
import ru.spliterash.springspigot.annotations.importSpringSpigotBeans.ImportSpringSpigotBeans

@Configuration
@ExternalContextOnly
@ImportSpringSpigotBeans(beans = [BukkitCloudCommandRegister::class])
class UmaCloudSuperExternalBukkitConfiguration