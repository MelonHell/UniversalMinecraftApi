plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val pluginName = "UmaCore"

bukkit {
    name = pluginName
    main = "ru.melonhell.uma.core.bukkit.dist.UmaCoreBukkitPlugin"
    apiVersion = "1.13"
    authors = listOf("MelonHell")
    depend = listOf("SpringSpigot")
    softDepend = listOf("KotlinMc")
    libraries = listOf()
}

tasks {
    shadowJar {
        val shadowPath = "ru.melonhell.uma.core.libs."
        archiveBaseName.set(pluginName)
        archiveClassifier.set("bukkit")
        minimize()

        dependencies {
          rootProject.findProject(":core")!!.allprojects.forEach {
                include(dependency(it))
            }
        }
    }
    assemble.get().dependsOn(shadowJar)
    jar.get().enabled = false
}