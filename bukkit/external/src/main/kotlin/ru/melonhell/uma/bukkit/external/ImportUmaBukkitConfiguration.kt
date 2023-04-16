package ru.melonhell.uma.bukkit.external

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import ru.melonhell.uma.bukkit.internal.cloud.BukkitCloudCommandBeanPostProcessor
import ru.melonhell.uma.common.internal.camera.CameraService
import ru.melonhell.uma.common.internal.cloud.CloudCommandRegistrar
import ru.spliterash.springspigot.annotations.importSpringSpigotBeans.ImportSpringSpigotBeans

@Configuration
@ImportSpringSpigotBeans(beans = [CloudCommandRegistrar::class, CameraService::class])
@Import(BukkitCloudCommandBeanPostProcessor::class)
class ImportUmaBukkitConfiguration