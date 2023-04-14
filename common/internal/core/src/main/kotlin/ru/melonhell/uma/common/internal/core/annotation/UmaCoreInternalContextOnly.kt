package ru.melonhell.uma.common.internal.core.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
//@ConditionalOnBean(UmaCoreCommonConfiguration::class)
annotation class UmaCoreInternalContextOnly