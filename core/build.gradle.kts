dependencies {
    // Dependencies
    compileOnly("com.mysql:mysql-connector-j:8.3.0")
    compileOnly("org.xerial:sqlite-jdbc:3.46.0.0")

    // Modules
    api(project(":common"))
}

tasks {
    shadowJar {
        relocate("org.lushplugins.lushlib", "org.lushplugins.minecraftmodules.libraries.lushlib")

        dependsOn(project(":common").tasks.named("shadowJar"))
    }
}

publishing {
    repositories {
        maven {
            name = "lushReleases"
            url = uri("https://repo.lushplugins.org/releases")
            credentials(PasswordCredentials::class)
            authentication {
                isAllowInsecureProtocol = true
                create<BasicAuthentication>("basic")
            }
        }

        maven {
            name = "lushSnapshots"
            url = uri("https://repo.lushplugins.org/snapshots")
            credentials(PasswordCredentials::class)
            authentication {
                isAllowInsecureProtocol = true
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString() + ".minecraftmodules"
            artifactId = project.name
            version = rootProject.version.toString()
            from(project.components["java"])
        }
    }
}