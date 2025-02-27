dependencies {
    implementation("fr.skytasul:glowingentities:1.3.5")
}

tasks {
    shadowJar {
        relocate("org.lushplugins.lushlib", "org.lushplugins.minecraftmodules.libraries.lushlib")
    }
}