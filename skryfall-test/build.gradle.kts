plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    implementation(Libs.kotlin)

    api(project(":skryfall-core"))
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