plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.retrofit)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    implementation(project(":scryfall-api-core"))
}
