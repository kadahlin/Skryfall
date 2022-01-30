load("@rules_java//java:defs.bzl", "java_binary")
load("@io_bazel_rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library", "kt_jvm_test")

kt_jvm_library(
    name = "sample",
    visibility = ["//visibility:private"],
    srcs = glob(["src/main/**/*.kt"]),
    deps = [
        "//skryfall",
    ],
)

kt_jvm_test(
    name = "sample_test",
    test_class = "com.kyledahlin.sample.ExampleTest",
    srcs = glob(["src/test/**/*.kt"]),
    deps = [
        "//skryfall-test",
        "@maven//:junit_junit"
    ],
)

java_binary(
    name = "sample_app",
    main_class = "com.kyledahlin.sample.MainKt",
    runtime_deps = [":sample"],
)