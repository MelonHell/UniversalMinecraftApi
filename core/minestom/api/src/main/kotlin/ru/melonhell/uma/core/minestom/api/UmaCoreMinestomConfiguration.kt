package ru.melonhell.uma.core.minestom.api

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import ru.melonhell.uma.core.common.api.UmaCoreCommonConfiguration

@Configuration
@ComponentScan("ru.melonhell.uma.core.minestom")
@Import(UmaCoreCommonConfiguration::class)
class UmaCoreMinestomConfiguration