load("@io_bazel_rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library")

kt_jvm_library(
    name = "skryfall",
    srcs = glob(["**/*.kt"]),
    visibility = ["//visibility:public"],
    deps = [
        "//third_party:deps",
    ],
)