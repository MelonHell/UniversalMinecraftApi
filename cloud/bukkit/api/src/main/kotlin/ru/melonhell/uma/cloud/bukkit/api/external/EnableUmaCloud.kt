package ru.melonhell.uma.cloud.bukkit.api.external

import org.springframework.context.annotation.Import


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(EnableUmaCloudConfiguration::class)
annotation class EnableUmaCloud
