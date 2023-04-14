package ru.melonhell.uma.common.internal.core.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
//@ConditionalOnMissingBean(UmaCoreCommonConfiguration::class)
annotation class UmaCoreExternalContextOnly