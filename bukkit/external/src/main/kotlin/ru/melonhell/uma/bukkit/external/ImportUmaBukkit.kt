package ru.melonhell.uma.bukkit.external

import org.springframework.context.annotation.Import


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(ImportUmaBukkitConfiguration::class)
annotation class ImportUmaBukkit
