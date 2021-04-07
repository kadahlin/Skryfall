plugins {
    kotlin("jvm") version PluginVersions.kotlin apply false
    id("org.jetbrains.dokka") version "1.4.10.2"
    id("org.jmailen.kotlinter") version "3.2.0"
}

allprojects {

    group = "com.kyledahlin"
    version = "0.4-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    apply(plugin = "maven-publish")
    configure<PublishingExtension> {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/kadahlin/Skryfall")
                credentials {
                    username = System.getenv("GITHUB_PACKAGES_USERNAME")
                    password = System.getenv("GITHUB_PACKAGES_TOKEN")
                }
            }
        }
    }
}
