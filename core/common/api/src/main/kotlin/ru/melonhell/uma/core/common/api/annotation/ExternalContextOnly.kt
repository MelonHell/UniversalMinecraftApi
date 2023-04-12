package ru.melonhell.uma.core.common.api.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import ru.melonhell.uma.core.common.api.UmaCoreCommonConfiguration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@ConditionalOnMissingBean(UmaCoreCommonConfiguration::class)
annotation class ExternalContextOnly