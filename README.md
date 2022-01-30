# Skryfall

This is an unofficial library for type safe access to the [scryfall api](https://scryfall.com/docs/api) using Bazel. I made this as a utility for another project and figured it would make a good library. The API is in no
way final and is subject to change with additional releases. Further documentation will be coming but for now a sample
usage can be seen in the `/sample` module. Builders for the objects returned by the client for use in testing are found
in the `skryfall-test` module.

## To use:

###Bazel

To use skryfall you must declare the provided artifact list to satisfy the 
transitive dependencies.

WORKSPACE:

```
git_repository(
    name = "skryfall",
    remote = "https://github.com/kadahlin/Skryfall.git",
    branch = "master"
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@skryfall//skryfall:skryfall_deps.bzl", "SKRYFALL_ARTIFACTS")
load("@skryfall//skryfall-test:skryfall-test_deps.bzl", "SKRYFALL_TEST_ARTIFACTS")

maven_install(
    artifacts = [
    <your deps>    
    ] + SKRYFALL_ARTIFACTS + SKRYFALL_TEST_ARTIFACTS,
    repositories = [
        "https://repo1.maven.org/maven2"
    ]
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

Older skryfall versions are available in github packages, gradle integration 
of the bazel module is a WIP.
