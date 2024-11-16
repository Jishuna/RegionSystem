plugins {
    id("java")
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.21.3-R0.1-SNAPSHOT") // Don't actually shade these
    implementation("it.unimi.dsi:fastutil-core:8.5.15") // Don't actually shade these
}