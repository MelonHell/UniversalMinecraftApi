plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val pluginName = "UmaCloud"

bukkit {
    name = pluginName
    main = "ru.melonhell.uma.cloud.bukkit.dist.UmaCloudBukkitPlugin"
    apiVersion = "1.13"
    authors = listOf("MelonHell")
    depend = listOf("UmaCore")
    libraries = listOf(
        "cloud.commandframework:cloud-paper:1.8.2",
        "cloud.commandframework:cloud-annotations:1.8.2"
    )
}

tasks {
    shadowJar {
        val shadowPath = "ru.melonhell.uma.cloud.libs."
        archiveBaseName.set(pluginName)
        archiveClassifier.set("bukkit")
        minimize()

        dependencies {
           rootProject.findProject(":cloud")!!.allprojects.forEach {
                include(dependency(it))
            }

        }
    }
    assemble.get().dependsOn(shadowJar)
    jar.get().enabled = false
}