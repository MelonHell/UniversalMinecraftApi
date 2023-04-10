package ru.melonhell.uma.cloud.bukkit.impl

import cloud.commandframework.annotations.AnnotationParser
import cloud.commandframework.arguments.parser.ParserParameters
import cloud.commandframework.arguments.parser.StandardParameters
import cloud.commandframework.bukkit.CloudBukkitCapabilities
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator
import cloud.commandframework.meta.CommandMeta
import cloud.commandframework.paper.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import ru.melonhell.uma.cloud.common.api.CloudCommand
import ru.melonhell.uma.core.bukkit.api.wrappers.BukkitCommandSender.Companion.unwrap
import ru.melonhell.uma.core.bukkit.api.wrappers.BukkitCommandSender.Companion.wrap
import ru.melonhell.uma.core.common.api.wrappers.UmaCommandSender
import java.util.function.Function


@Configuration
class BukkitCloudCommandRegisterer(
    javaPlugin: JavaPlugin
) : BeanPostProcessor {
    private val manager: PaperCommandManager<UmaCommandSender>
    private val annotationParser: AnnotationParser<UmaCommandSender>

    init {
        val executionCoordinatorFunction =
            AsynchronousCommandExecutionCoordinator.builder<UmaCommandSender>().build()
        manager = PaperCommandManager(
            javaPlugin,
            executionCoordinatorFunction,
            { it.wrap() },
            { it.unwrap() }
        )
        if (manager.hasCapability(CloudBukkitCapabilities.BRIGADIER)) manager.registerBrigadier()
        if (manager.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) manager.registerAsynchronousCompletions()

        val commandMetaFunction = Function<ParserParameters, CommandMeta> { p: ParserParameters ->
            CommandMeta
                .simple()
                .with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description"))
                .build()
        }

        annotationParser = AnnotationParser(manager, UmaCommandSender::class.java, commandMetaFunction)
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (bean.javaClass.isAnnotationPresent(CloudCommand::class.java)) annotationParser.parse(bean)
        return bean
    }
}