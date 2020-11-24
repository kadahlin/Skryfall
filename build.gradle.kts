plugins {
    kotlin("jvm") version PluginVersions.kotlin apply false
    id("org.jetbrains.dokka") version "1.4.10.2"
    id("org.jmailen.kotlinter") version "3.2.0"
}

group = "com.kyledahlin"
version = "1.0-SNAPSHOT"

allprojects {

    group = "com.kyledahlin"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}
