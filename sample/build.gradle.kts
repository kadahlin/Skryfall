plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.coroutines)

    implementation(project(":skryfall-coroutines"))
}

application.mainClass.set("com.kyledahlin.sample.MainKt")
