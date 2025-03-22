import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupCustomMatchersModule") {
    description = "Creates the base module and files for the custom matchers lesson"
    module.set("matchers:custom")
    doLast {
        createFiles(
            "metrics",
            main to "IdentifierGenerator.kt",
            test to "IdentifierGeneratorTest.kt",
        )
        createFiles(
            "metrics.matchers",
            test to "beEven.kt",
            test to "beCloseTo.kt",
        )
    }
}
