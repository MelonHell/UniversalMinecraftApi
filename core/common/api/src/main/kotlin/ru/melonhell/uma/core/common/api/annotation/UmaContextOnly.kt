package ru.melonhell.uma.core.common.api.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import ru.melonhell.uma.core.common.api.UmaCoreCommonConfiguration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@ConditionalOnBean(UmaCoreCommonConfiguration::class)
annotation class UmaContextOnly {
}