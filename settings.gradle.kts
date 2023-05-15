pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.spliterash.ru/group/")
    }
}

rootProject.name = "Uma"

val platforms = listOf("common", "bukkit", "minestom")
val types = listOf("internal", "external", "plugin")
val features = listOf("core", "cloud", "camera")

platforms.forEach { platform ->
    if (File(platform).exists()) {
        include(platform)
    }
    types.forEach { type ->
        if (File("$platform/$type").exists()) {
            include("$platform:$type")
        }
        features.forEach { feature ->
            if (File("$platform/$type/$feature").exists()) {
                include("$platform:$type:$feature")
            }
        }
    }
}