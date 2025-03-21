import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupPrngModule") {
    description = "Creates the base module and files for the PRNG based generator lesson"
    module.set("pbt:arbitrary:prng")
    doLast {
        createFiles(
            "lists",
            main to "average.kt",
            test to "AverageTest.kt",
        )
        createFiles(
            "maps",
            test to "IntStringMapTest.kt",
        )
    }
}
