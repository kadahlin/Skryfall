load("@rules_java//java:defs.bzl", "java_binary")
load("@io_bazel_rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library")
load("@io_bazel_rules_kotlin//kotlin:lint.bzl", "ktlint_fix", "ktlint_test")

kt_jvm_library(
    name = "sample",
    visibility = ["//visibility:private"],
    srcs = glob(["src/main/**/*.kt"]),
    deps = [
        "//skryfall",
    ],
)

java_binary(
    name = "sample_app",
    main_class = "com.kyledahlin.sample.MainKt",
    runtime_deps = [":sample"],
)