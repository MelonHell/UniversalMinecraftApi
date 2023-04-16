package ru.melonhell.uma.bukkit.plugin

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Configuration
import ru.melonhell.nmsentitylib.NmsEntityLib
import ru.melonhell.nmsentitylib.app.NmsEntityLibPlugin
import ru.spliterash.springspigot.annotations.importSpringSpigotBeans.ImportSpringSpigotBeans

@Configuration
@ConditionalOnClass(NmsEntityLibPlugin::class)
@ImportSpringSpigotBeans(
    plugin = NmsEntityLibPlugin::class,
    beans = [NmsEntityLib::class]
)
class NmsEntityLibImportConfiguration