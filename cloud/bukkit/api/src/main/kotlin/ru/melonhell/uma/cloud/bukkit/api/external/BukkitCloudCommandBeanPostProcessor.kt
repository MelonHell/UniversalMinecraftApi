package ru.melonhell.uma.cloud.bukkit.api.external

import org.bukkit.plugin.java.JavaPlugin
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import ru.melonhell.uma.cloud.common.api.CloudCommand

@Configuration
class BukkitCloudCommandBeanPostProcessor(
    private val javaPlugin: JavaPlugin,
    private val register: BukkitCloudCommandRegister,
) : BeanPostProcessor {

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (bean.javaClass.isAnnotationPresent(CloudCommand::class.java)) {
            register.registerCommand(javaPlugin, bean)
            javaPlugin.getLogger().info("registered command ${bean.javaClass.name} in plugin ${javaPlugin.name}")
        }
        return bean
    }
}