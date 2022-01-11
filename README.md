# Skryfall

This is an unofficial library for type safe access to the [scryfall api](https://scryfall.com/docs/api) using Kotlin
Multiplatform. I made this as a utility for another project and figured it would make a good library. The API is in no
way final and is subject to change with additional releases. Further documentation will be coming but for now a sample
usage can be seen in the `/sample` module. Builders for the objects returned by the client for use in testing are found in the `skryfall-test` package.

## To use:

Include the package in your common source set

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.kyledahlin:skryfall:0.9.0")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation("com.kyledahlin.skryfall-test:0.9.0")
            }
        }
    }
}
```

Or use directly in an android/JVM package

```kotlin
repositories {
    maven(url = "https://maven.pkg.github.com/kadahlin/Skryfall") {
        credentials {
            username = "<your github packages username>"
            password = "<your github packages password>"
        }
    }
}

dependencies {
    implementation("com.kyledahlin:skryfall:0.9.0")
    testImplementation("com.kyledahlin.skryfall-test:0.9.0")
}
```

Skryfall is currently hosted on Github packages so the necessary password and username are required. I will look into
other repositories as setting up github packages is less than ideal.
