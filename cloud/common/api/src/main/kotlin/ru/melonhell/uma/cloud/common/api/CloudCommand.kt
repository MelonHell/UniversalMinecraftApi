package ru.melonhell.uma.cloud.common.api

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
annotation class CloudCommand (
    @get:AliasFor(annotation = Component::class) val value: String = ""
)