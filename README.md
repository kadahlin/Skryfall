# Skryfall

This is an unofficial library for type safe access to the [scryfall api](https://scryfall.com/docs/api) using Kotlin
Multiplatform. I made this as a utility for another project and figured it would make a good library. The API is in no
way final and is subject to change with additional releases. Further documentation will be coming but for now a sample
usage can be seen in the `/sample` module. Builders for the objects returned by the client for use in testing are found in the `skryfall-test` package.

## To use:

###Bazel

WORKSPACE

```
git_repository(
    name = "skryfall",
    remote = "https://github.com/kadahlin/Skryfall.git",
    branch = "master"
)
```

BUILD
```
kt_jvm_library(
    ...
    deps = [
        "@skryfall//skryfall",
        "@skryfall//skryfall-test"
    ]
)
```

Older skryfall versions are available in github packages
