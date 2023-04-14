plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

bukkit {
    name = rootProject.name
    main = "ru.melonhell.uma.bukkit.plugin.UmaCoreBukkitPlugin"
    apiVersion = "1.13"
    authors = listOf("MelonHell")
    depend = listOf("SpringSpigot")
    softDepend = listOf("KotlinMc")
    libraries = listOf()
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