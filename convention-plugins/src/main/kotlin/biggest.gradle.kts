import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupBiggestModule") {
    description = "Creates the base module and files for the testing introductory lesson"
    module.set("pbt:biggest")
    doLast {
        createFiles(
            "biggest",
            main to "Biggest.kt",
            test to "BiggestTest.kt",
        )
    }
}
