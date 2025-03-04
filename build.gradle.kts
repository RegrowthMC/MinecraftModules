plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version("8.3.0")
}

group = "org.lushplugins"
version = "1.0.13"

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.gradleup.shadow")

    tasks {
        shadowJar {
            if (project.name != "common") {
                relocate("org.lushplugins.lushlib", "org.lushplugins.minecraftmodules.libraries.lushlib")
            }
        }
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenLocal()
        mavenCentral() // LuckPerms, GlowingEntities
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://repo.papermc.io/repository/maven-public/") // Paper
        maven("https://repo.lushplugins.org/snapshots/") // LushLib
        maven("https://repo.opencollab.dev/main/") // Floodgate
        maven("https://repo.helpch.at/releases/") // PlaceholderAPI
        maven("https://jitpack.io/") // NightCore
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))

        registerFeature("optional") {
            usingSourceSet(sourceSets["main"])
        }

        withSourcesJar()
    }

    tasks {
        build {
            dependsOn(shadowJar)
        }

        shadowJar {
            archiveFileName.set("${project.name}-${project.version}.jar")
        }
    }
}