plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    `maven-publish`
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    version = "1.0.0-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        withSourcesJar()
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io/")
        maven("https://repo.papermc.io/repository/maven-public/") // Paper API
        maven("https://repo.spliterash.ru/group/")
    }

    dependencies {
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

        api("org.springframework:spring-context:6.0.6")
        api("javax.annotation:javax.annotation-api:1.3.2")

        api("net.kyori:adventure-api:4.13.0")
    }
}

// Тут крч магическая конфигурация зависимостей всех модулей
childProjects.values.forEach { rootProj ->
    rootProj.childProjects.values.forEach { platformProj ->
        platformProj.childProjects.values.forEach { typeProj ->

            // Ко всему, что баккит подключаем апи баккита
            if (platformProj.name == "bukkit") {
                typeProj.dependencies {
                    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
                    compileOnly("ru.spliterash:spring-spigot:1.0.6")
                }
            }

            // Ко всему, что майнстом подключаем апи майнстома
            if (platformProj.name == "minestom") {
                typeProj.dependencies {
                    compileOnly("com.github.Minestom.Minestom:Minestom:8ad2c7701f")
                }
            }

            // Если это не dist, то подключаем его к dist
            // И публишим в репу
            if (typeProj.name != "dist") {
                findProject(":${rootProj.name}:${platformProj.name}:dist")?.let {
                    it.dependencies { api(typeProj) }
                    println("${it.path} depends on ${typeProj.path}")
                }
                typeProj.configurePublishing()
            }

            // Если это не api, подключаем к нему api
            if (typeProj.name != "api") {
                findProject(":${rootProj.name}:${platformProj.name}:api")?.let {
                    typeProj.dependencies { api(it) }
                    println("${typeProj.path} depends on ${it.path}")
                }
            }

            // Если это не common, подключаем к нему common
            if (platformProj.name != "common") {
                findProject(":${rootProj.name}:common:${typeProj.name}")?.let {
                    typeProj.dependencies { api(it) }
                    println("${typeProj.path} depends on ${it.path}")
                }
            }

            // Если это не core, подключаем к нему core (но только апишки)
            if (rootProj.name != "core") {
                findProject(":core:${platformProj.name}:api")?.let {
                    typeProj.dependencies { api(it) }
                    println("${typeProj.path} depends on ${it.path}")
                }
            }
        }
    }
}

fun Project.configurePublishing() {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "ru.melonhell.uma"
                artifactId = "uma-" + this@configurePublishing.path.substring(1).replace(":", "-")
                from(components["java"])
            }
        }

        repositories {
            mavenLocal()
            maven {
                name = "repo.spliterash"
                url = uri("https://repo.spliterash.ru/" + rootProject.name)
                credentials {
                    username = findProperty("SPLITERASH_NEXUS_USR")?.toString()
                    password = findProperty("SPLITERASH_NEXUS_PSW")?.toString()
                }
            }
        }
    }
}