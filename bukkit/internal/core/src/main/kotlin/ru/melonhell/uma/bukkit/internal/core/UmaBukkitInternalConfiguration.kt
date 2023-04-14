package ru.melonhell.uma.bukkit.internal.core

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import ru.melonhell.uma.common.internal.core.UmaCommonInternalConfiguration

@Configuration
@ComponentScan("ru.melonhell.uma.bukkit.internal")
@Import(UmaCommonInternalConfiguration::class)
class UmaBukkitInternalConfiguration