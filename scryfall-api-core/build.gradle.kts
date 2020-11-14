plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.4.10"
    kotlin("kapt")
}

dependencies {
    implementation(Libs.kotlin)
    api(Libs.retrofit)
    api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
}
