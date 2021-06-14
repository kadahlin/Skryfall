# Skryfall

This is an unoffical library to access the scryfall api for Mtg resources. I made this as a utility for another project
and figured it would make a good library. This is a kotlin first API in that queries are composed with infix functions
and the coroutines client is the recommended approach. A callback based client is provided in the `skryfall-callback`
package for non-coroutine use cases. The API is in no way final and is subject to change with additional releases.
Further documentation will be coming but for now a sample usage of the coroutines client can be seen in the `/sample`
module.

### To use:

```kotlin
repositories {
    maven(url = "https://maven.pkg.github.com/kadahlin/Skryfall") {
            credentials {
                username = <your github packages username>
                password = <your github packages password>
            }
        }
}

dependencies {
    // Coroutine client (recommended)
    implementation("com.kyledahlin:skryfall-coroutines:0.5")

    // callback client
    implementation("com.kyledahlin:skryfall-callback:0.5")
}
```

Skryfall is currently hosted on Github packages so the necessary password and username are required. I will look into other repositories as setting up github packages is less than ideal.
