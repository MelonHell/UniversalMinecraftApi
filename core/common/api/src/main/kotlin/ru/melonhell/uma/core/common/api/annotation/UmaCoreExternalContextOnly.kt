package ru.melonhell.uma.core.common.api.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import ru.melonhell.uma.core.common.api.UmaCoreCommonConfiguration

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ConditionalOnMissingBean(UmaCoreCommonConfiguration::class)
annotation class UmaCoreExternalContextOnly