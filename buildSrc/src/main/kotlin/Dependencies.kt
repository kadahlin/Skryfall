object PluginVersions {
    const val dokka = "0.10.1"
    const val kotlin = "1.4.10"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${PluginVersions.kotlin}"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.0"
    const val jvmSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
}

object Publishing {
    const val groupId = "com.kyledahlin"
    const val version = "0.5"
}