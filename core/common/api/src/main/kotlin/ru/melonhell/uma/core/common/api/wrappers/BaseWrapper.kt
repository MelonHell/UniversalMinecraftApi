package ru.melonhell.uma.core.common.api.wrappers

interface BaseWrapper {
    val handle: Any
    fun hc() = handle.hashCode()
    fun eq(other: Any?) = if (this.javaClass == other?.javaClass) handle == (other as BaseWrapper).handle else false
}