rootProject.name = "skryfall-test"

pluginManagement {
    includeBuild("../build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    includeBuild("../build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}