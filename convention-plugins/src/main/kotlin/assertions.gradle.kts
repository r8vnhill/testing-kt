import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupAssertionsModule") {
    description = "Creates the base module and files for the assertions lesson"
    module.set("assertions")

    doLast {
        createFiles(
            "calculator",
            test to "CalculatorTest.kt",
            main to "Calculator.kt",
        )
        createFiles(
            "assertions",
            test to "assertTrue.kt",
            test to "assertIsOrdered.kt",
        )
    }
}
