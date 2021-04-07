plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.retrofit)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    implementation(project(":scryfall-api-core"))
}

publishing {
    publications {
        create<MavenPublication>("coroutines") {
            artifactId = "scryfall-api-coroutines"
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