import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupArbCompositionModule") {
    description = "Creates the base module and files for the arbitrary composition lesson"
    module.set("pbt:arbitrary:composition")
    doLast {
        createFiles(
            "forms",
            main to "UserInput.kt",
            test to "FormValidationTest.kt",
        )
        createFiles(
            "config",
            test to "ConfigGenerators.kt"
        )
    }
}
