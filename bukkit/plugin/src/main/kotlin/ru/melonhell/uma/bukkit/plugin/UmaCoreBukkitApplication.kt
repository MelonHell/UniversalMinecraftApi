package ru.melonhell.uma.bukkit.plugin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import ru.melonhell.uma.common.internal.core.UmaCommonInternalConfiguration

@SpringBootApplication
@Import(UmaCommonInternalConfiguration::class)
class UmaCoreBukkitApplication