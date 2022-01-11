rootProject.name = "sample"

pluginManagement {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    includeBuild("../skryfall")
    includeBuild("../skryfall-test")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}