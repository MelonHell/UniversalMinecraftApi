package ru.melonhell.uma.cloud.minestom.impl

import cloud.commandframework.annotations.AnnotationParser
import cloud.commandframework.arguments.parser.ParserParameters
import cloud.commandframework.arguments.parser.StandardParameters
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator
import cloud.commandframework.meta.CommandMeta
import io.github.openminigameserver.cloudminestom.MinestomCommandManager
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import ru.melonhell.uma.cloud.common.api.CloudCommand
import ru.melonhell.uma.core.common.api.wrappers.UmaCommandSender
import ru.melonhell.uma.core.minestom.api.wrappers.MinestomCommandSender.Companion.unwrap
import ru.melonhell.uma.core.minestom.api.wrappers.MinestomCommandSender.Companion.wrap
import java.util.function.Function


@Configuration
class MinestomCloudCommandRegisterer : BeanPostProcessor {
    private val manager: MinestomCommandManager<UmaCommandSender>
    private val annotationParser: AnnotationParser<UmaCommandSender>

    init {
        val executionCoordinatorFunction =
            AsynchronousCommandExecutionCoordinator.builder<UmaCommandSender>().build()
        manager = MinestomCommandManager(executionCoordinatorFunction, { it.wrap() }, { it.unwrap() })

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