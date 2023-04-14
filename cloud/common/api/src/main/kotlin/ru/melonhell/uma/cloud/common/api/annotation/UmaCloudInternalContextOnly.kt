package ru.melonhell.uma.cloud.common.api.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import ru.melonhell.uma.cloud.common.api.UmaCloudCommonConfiguration
import ru.melonhell.uma.cloud.common.api.UmaCloudCommonEmptyBean
import ru.melonhell.uma.core.common.api.UmaCoreCommonConfiguration

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ConditionalOnBean(UmaCloudCommonEmptyBean::class)
annotation class UmaCloudInternalContextOnly