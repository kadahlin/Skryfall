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
    api(Libs.jvmSerialization)
    api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    api(project(":skryfall-core"))

}

publishing {
    publications {
        create<MavenPublication>("skryfall-callback") {
            pom {
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
            }
            artifactId = "skryfall-callback"
            groupId = Publishing.groupId
            version = Publishing.version
            from(components.findByName("java"))
        }
    }
}
