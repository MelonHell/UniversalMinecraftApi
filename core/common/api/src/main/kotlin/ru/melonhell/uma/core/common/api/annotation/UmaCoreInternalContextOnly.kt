package ru.melonhell.uma.core.common.api.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import ru.melonhell.uma.core.common.api.UmaCoreCommonConfiguration

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ConditionalOnBean(UmaCoreCommonConfiguration::class)
annotation class UmaCoreInternalContextOnly