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

include("intro")
include("pbt:biggest")
include("bdd")
include("pbt:arbitrary:prng")