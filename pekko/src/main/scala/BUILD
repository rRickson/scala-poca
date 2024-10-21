load("@io_bazel_rules_scala//scala:scala.bzl", "scala_library")

scala_library(
    name = "my_project_lib",
    srcs = glob(["src/main/scala/*.scala","src/main/scala/**/*.scala"]),
    deps = [
        "@maven//:org_apache_pekko_pekko_actor_2_13",
        "@maven//:org_apache_pekko_pekko_http_2_13",
        "@maven//:org_apache_pekko_pekko_http_spray_json_2_13",
        "@maven//:org_postgresql_postgresql",
    ],
)
