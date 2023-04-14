package ru.melonhell.uma.bukkit.internal.cloud

import org.bukkit.plugin.java.JavaPlugin
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitExtension.Companion.wrap
import ru.melonhell.uma.common.internal.cloud.CloudCommand
import ru.melonhell.uma.common.internal.cloud.CloudCommandRegistrar

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