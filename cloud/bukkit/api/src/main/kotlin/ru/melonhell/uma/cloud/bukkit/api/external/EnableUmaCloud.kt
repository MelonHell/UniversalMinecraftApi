package ru.melonhell.uma.cloud.bukkit.api.external

import org.springframework.context.annotation.Import


@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Import(UmaCloudExternalBukkitConfiguration::class)
annotation class EnableUmaCloud
