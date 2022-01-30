load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

rules_kotlin_version = "v1.5.0-beta-4"
rules_kotlin_sha = "6cbd4e5768bdfae1598662e40272729ec9ece8b7bded8f0d2c81c8ff96dc139d"
http_archive(
    name = "io_bazel_rules_kotlin",
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin_release.tgz" % rules_kotlin_version],
    sha256 = rules_kotlin_sha,
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")
kotlin_repositories()

load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")
kt_register_toolchains()

http_archive(
    name = "rules_jvm_external",
    sha256 = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca",
    strip_prefix = "rules_jvm_external-4.2",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % "4.2",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "junit:junit:4.13.2",
        "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0",
        "io.ktor:ktor-client:1.6.7",
        "io.ktor:ktor-client-core-jvm:1.6.7",
        "io.ktor:ktor-client-serialization-jvm:1.6.7",
        "io.ktor:ktor-client-logging-jvm:1.6.7",
        "io.ktor:ktor-client-json:1.6.7",
        "io.ktor:ktor-client-apache:1.6.7"
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)
