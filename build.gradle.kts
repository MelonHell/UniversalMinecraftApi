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
        maven("https://repo.spliterash.ru/group/")
        maven("https://repo.papermc.io/repository/maven-public/") // Paper API
        maven("https://repo.dmulloy2.net/repository/public/") // ProtocolLib
    }

    dependencies {
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

        api("org.greenrobot:eventbus-java:3.3.1")

        api("org.springframework.boot:spring-boot-starter:3.0.5")
        api("javax.annotation:javax.annotation-api:1.3.2")

        api("net.kyori:adventure-api:4.13.0")
    }
}

fun Project.apiDependency(dependency: Project) {
    dependencies { api(dependency) }
    println("${this.path} depends on ${dependency.path}")
}

// Тут крч магическая конфигурация зависимостей всех модулей
childProjects.values.forEach { platformProj ->

    // Ко всему, что баккит подключаем апи баккита
    if (platformProj.name == "bukkit") {
        platformProj.allprojects {
            dependencies {
                compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
                compileOnly("ru.spliterash:spring-spigot:1.0.11")

                compileOnly("ru.melonhell:NmsEntityLib:1.0.2")
                compileOnly("com.comphenix.protocol:ProtocolLib:5.0.0-SNAPSHOT")
            }
        }
    }

    // Ко всему, что майнстом подключаем апи майнстома
    if (platformProj.name == "minestom") {
        platformProj.allprojects {
            dependencies {
                compileOnly("com.github.Minestom.Minestom:Minestom:8ad2c7701f")
            }
        }
    }

    platformProj.childProjects.values.forEach { typeProj ->

        if (typeProj.name != "plugin") {
            findProject(":${platformProj.name}:plugin")?.apiDependency(typeProj)
            typeProj.configurePublishing()
        }

        if (typeProj.name != "internal") {
            findProject(":${platformProj.name}:internal")?.let {
                typeProj.apiDependency(it)
            }
        }

        typeProj.childProjects.values.forEach { featureProj ->

            featureProj.configurePublishing()

            typeProj.apiDependency(featureProj)

            if (featureProj.name != "core") {
                findProject(":${platformProj.name}:${typeProj.name}:core")?.let {
                    featureProj.apiDependency(it)
                }
            }

            if (platformProj.name != "common") {
                findProject(":common:${typeProj.name}:${featureProj.name}")?.let {
                    featureProj.apiDependency(it)
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