import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupStatsModule") {
    description = "Creates the base module and files for the statistics collection lesson"
    module.set("pbt:arbitrary:stats")
    doLast {
        createFiles(
            "zoo",
            main to "Animal.kt",
            test to "AnimalTest.kt",
        )
        createFiles(
            "geometry",
            main to "Triangle.kt",
            test to "TriangleTest.kt",
        )
    }
}