plugins {
    id("net.minecrell.plugin-yml.paper") version "0.6.0-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

paper {
    name = rootProject.name
    main = "ru.melonhell.uma.bukkit.plugin.UmaCoreBukkitPlugin"
    apiVersion = "1.19"
    authors = listOf("MelonHell")
    depends {
        register("GlobalLibraryLoader")
        register("SpringSpigot")
        register("ProtocolLib")
        register("NmsEntityLib")
    }
    loadAfter {
        register("GlobalLibraryLoader")
        register("SpringSpigot")
        register("ProtocolLib")
        register("NmsEntityLib")
    }
}

tasks {
    shadowJar {
        val shadowPath = "ru.melonhell.uma.core.libs."
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("bukkit")
        minimize()

        dependencies {
          rootProject.allprojects.forEach {
                include(dependency(it))
            }
        }
    }
    assemble.get().dependsOn(shadowJar)
    jar.get().enabled = false
}