plugins {
    id("com.kyledahlin.mpp")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:${Libs.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization:${Libs.ktorVersion}")
                implementation("io.ktor:ktor-client-logging:${Libs.ktorVersion}")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:${Libs.ktorVersion}")
                implementation("ch.qos.logback:logback-classic:1.2.10")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:${Libs.ktorVersion}")
                implementation("ch.qos.logback:logback-classic:1.2.10")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${Libs.ktorVersion}")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:${Libs.ktorVersion}")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:${Libs.ktorVersion}")
            }
        }
    }
}