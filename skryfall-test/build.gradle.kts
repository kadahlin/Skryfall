plugins {
    id("com.kyledahlin.mpp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.kyledahlin.skryfall:skryfall")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("skryfall-test") {
            pom {
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
            }
            artifactId = "skryfall-test"
            groupId = Publishing.groupId
            version = Publishing.version
            from(components.findByName("java"))
        }
    }
}