plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.4.10"
    kotlin("kapt")
    `maven-publish`
}

apply(plugin = "org.jmailen.kotlinter")

dependencies {
    implementation(Libs.kotlin)
    api(Libs.retrofit)
    api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
}

publishing {
    publications {
        create<MavenPublication>("coroutines") {
            artifactId = "scryfall-api-core"
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "BrinkHorizon"
            credentials {
                username = "kyle@brinkhorizon.com"
                password =  { System.getenv("MAVEN_REPO_PASS").apply { println(this)} }()
            }
            url = uri("https://brinkhorizon.jfrog.io/artifactory/android/")
        }
    }
}
