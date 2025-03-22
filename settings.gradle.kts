rootProject.name = "testing"

pluginManagement {
    includeBuild("convention-plugins")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

include("assertions")
include("bdd")
include("ddt")
include("intro")
include("matchers:custom")
include("pbt:arbitrary:composition")
include("pbt:arbitrary:prng")
include("pbt:arbitrary:stats")
include("pbt:biggest")
include("pbt:str-cat")