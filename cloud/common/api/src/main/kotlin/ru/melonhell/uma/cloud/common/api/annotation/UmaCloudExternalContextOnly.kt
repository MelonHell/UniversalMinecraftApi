package ru.melonhell.uma.cloud.common.api.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import ru.melonhell.uma.cloud.common.api.UmaCloudCommonConfiguration
import ru.melonhell.uma.cloud.common.api.UmaCloudCommonEmptyBean

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ConditionalOnMissingBean(UmaCloudCommonEmptyBean::class)
annotation class UmaCloudExternalContextOnly