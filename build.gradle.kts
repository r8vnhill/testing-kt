plugins {
    alias(libs.plugins.detekt)
    id("arb-composition")
    id("assertions")
    id("bdd")
    id("biggest")
    id("custom-matchers")
    id("ddt")
    id("intro")
    id("jvm.conventions")
    id("prng")
    id("stats")
    id("str-cat")
    id("predef-matchers")
}

val projectGroup = extra["testing.group"]!! // Throws an exception if the property is not found
val projectVersion: String = libs.versions.testing.get()
val detektId: String = libs.plugins.detekt.get().pluginId
val detektFormattingModule = libs.detekt.formatting.get().module.toString()
val detektFormattingVersion = libs.detekt.formatting.get().version
val kotestBundle = libs.bundles.kotest

allprojects {
    group = projectGroup
    version = projectVersion
}

subprojects {
    apply(plugin = "jvm.conventions")
    apply(plugin = detektId)

    dependencies {
        detektPlugins("$detektFormattingModule:$detektFormattingVersion")
        testImplementation(kotestBundle)
    }
}