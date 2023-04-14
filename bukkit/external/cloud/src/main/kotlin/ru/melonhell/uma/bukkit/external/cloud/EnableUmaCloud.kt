package ru.melonhell.uma.bukkit.external.cloud

import org.springframework.context.annotation.Import
import ru.melonhell.uma.bukkit.external.cloud.EnableUmaCloudConfiguration


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(EnableUmaCloudConfiguration::class)
annotation class EnableUmaCloud
