load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_compiler_plugin")
load("@io_bazel_rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library", "kt_jvm_test")
load("@rules_jvm_external//:defs.bzl", "artifact")

kt_compiler_plugin(
    name = "serialization_plugin",
    compile_phase = True,
    id = "org.jetbrains.kotlin.serialization",
    stubs_phase = True,
    deps = [
        "@com_github_jetbrains_kotlin//:kotlinx-serialization-compiler-plugin",
    ],
)

kt_jvm_library(
    name = "deps",
    visibility = ["//visibility:public"],
    exported_compiler_plugins = [":serialization_plugin"],
    exports = [
        "@maven//:org_jetbrains_kotlinx_kotlinx_coroutines_core",
        "@maven//:org_jetbrains_kotlinx_kotlinx_serialization_json",
        "@maven//:io_ktor_ktor_client_core_jvm",
        "@maven//:io_ktor_ktor_client_serialization_jvm",
        "@maven//:io_ktor_ktor_client_logging_jvm",
        "@maven//:io_ktor_ktor_client",
        "@maven//:io_ktor_ktor_client_json",
        "@maven//:io_ktor_ktor_client_apache"
    ],
)