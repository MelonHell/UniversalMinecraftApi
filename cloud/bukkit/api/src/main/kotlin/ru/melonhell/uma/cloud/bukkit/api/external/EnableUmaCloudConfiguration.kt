package ru.melonhell.uma.cloud.bukkit.api.external

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import ru.melonhell.uma.cloud.bukkit.api.BukkitCloudCommandBeanPostProcessor
import ru.melonhell.uma.cloud.common.api.CloudCommandRegistrar
import ru.melonhell.uma.cloud.common.api.annotation.UmaCloudExternalContextOnly
import ru.spliterash.springspigot.annotations.importSpringSpigotBeans.ImportSpringSpigotBeans

@Configuration
@UmaCloudExternalContextOnly
@ImportSpringSpigotBeans(beans = [CloudCommandRegistrar::class])
@Import(BukkitCloudCommandBeanPostProcessor::class)
class EnableUmaCloudConfiguration