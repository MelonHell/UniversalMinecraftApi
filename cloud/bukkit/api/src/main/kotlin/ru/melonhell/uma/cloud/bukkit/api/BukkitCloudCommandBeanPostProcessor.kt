package ru.melonhell.uma.cloud.bukkit.api

import org.bukkit.plugin.java.JavaPlugin
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import ru.melonhell.uma.cloud.common.api.CloudCommand
import ru.melonhell.uma.cloud.common.api.CloudCommandRegistrar
import ru.melonhell.uma.core.bukkit.api.wrappers.BukkitPlugin.Companion.wrap

@Configuration
class BukkitCloudCommandBeanPostProcessor(
    private val javaPlugin: JavaPlugin,
    private val register: CloudCommandRegistrar,
) : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (bean.javaClass.isAnnotationPresent(CloudCommand::class.java)) {
            register.registerCommand(bean, javaPlugin.wrap())
        }
        return bean
    }
}