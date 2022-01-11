plugins {
    kotlin("jvm") version "1.6.10"
    application
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    implementation("com.kyledahlin.skryfall:skryfall")

    testImplementation("com.kyledahlin.skryfall:skryfall-test")
}

application.mainClass.set("com.kyledahlin.sample.MainKt")
