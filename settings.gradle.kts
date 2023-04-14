rootProject.name = "Uma"

val roots = listOf("core", "cloud", "camera")
val platforms = listOf("common", "bukkit", "minestom")
val types = listOf("api", "impl", "dist")

roots.forEach { root ->
    platforms.forEach { platform ->
        types.forEach { type ->
            if (File("$root/$platform/$type").exists()) {
                include("$root:$platform:$type")
            }
        }
    }
}