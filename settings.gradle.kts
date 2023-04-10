rootProject.name = "Uma"

//include(
//    "core:common:api",
//    "core:common:impl",
//
//    "core:bukkit:api",
//    "core:bukkit:impl",
//    "core:minestom:api",
//    "core:minestom:impl",
//
//    "cloud:common:api",
//    "cloud:common:impl",
//
//    "cloud:bukkit:api",
//    "cloud:bukkit:impl",
//    "cloud:minestom:api",
//    "cloud:minestom:impl",
//)

val roots = listOf("core", "cloud")
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