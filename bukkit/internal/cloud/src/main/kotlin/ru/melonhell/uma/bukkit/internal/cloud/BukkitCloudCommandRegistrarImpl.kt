package ru.melonhell.uma.bukkit.internal.cloud

import cloud.commandframework.CommandManager
import cloud.commandframework.annotations.AnnotationParser
import cloud.commandframework.arguments.parser.ParserParameters
import cloud.commandframework.arguments.parser.StandardParameters
import cloud.commandframework.bukkit.CloudBukkitCapabilities
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator
import cloud.commandframework.meta.CommandMeta
import cloud.commandframework.paper.PaperCommandManager
import org.bukkit.plugin.Plugin
import org.springframework.stereotype.Component
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitCommandSender.Companion.unwrap
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitCommandSender.Companion.wrap
import ru.melonhell.uma.bukkit.internal.core.wrappers.BukkitExtension.Companion.unwrap
import ru.melonhell.uma.common.internal.cloud.CloudCommandRegistrar
import ru.melonhell.uma.common.internal.core.wrappers.UmaCommandSender
import ru.melonhell.uma.common.internal.core.wrappers.UmaExtension
import java.util.function.Function

@Component
class BukkitCloudCommandRegistrarImpl(
    val globalPlugin: Plugin
) : CloudCommandRegistrar {
    private val plugins = hashMapOf<Plugin, BukkitCloudData>()

    private val parser = Function<ParserParameters, CommandMeta> { p: ParserParameters ->
        CommandMeta
            .simple()
            .with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description"))
            .build()
    }

    override fun registerCommand(obj: Any, plugin: UmaExtension?) {
        plugins.computeIfAbsent(plugin?.unwrap() ?: globalPlugin, ::createManager).annotationParser.parse(obj)
    }

    private fun createManager(plugin: Plugin): BukkitCloudData {
        val executionCoordinatorFunction = AsynchronousCommandExecutionCoordinator.builder<UmaCommandSender>().build()
        val manager = PaperCommandManager(
            plugin,
            executionCoordinatorFunction,
            { it.wrap() },
            { it.unwrap() }
        )

        if (manager.hasCapability(CloudBukkitCapabilities.BRIGADIER)) manager.registerBrigadier()
        if (manager.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) manager.registerAsynchronousCompletions()
        manager.setSetting(CommandManager.ManagerSettings.ALLOW_UNSAFE_REGISTRATION, true)
        val annotationParser = AnnotationParser(manager, UmaCommandSender::class.java, parser)

        return BukkitCloudData(manager, annotationParser)
    }


    private class BukkitCloudData(
        val manager: PaperCommandManager<UmaCommandSender>,
        val annotationParser: AnnotationParser<UmaCommandSender>
    )
}