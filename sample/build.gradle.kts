plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(Libs.kotlin)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.4.1")

    implementation(project(":scryfall-api-core"))
    implementation(project(":scryfall-api-coroutines"))
}

application.mainClassName = "com.kyledahlin.sample.MainKt"
