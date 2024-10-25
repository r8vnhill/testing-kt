import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupIntroModule") {
    description = "Creates the base module and files for the testing introductory lesson"
    module.set("intro")

    doLast {
        createFiles(
            "intro",
            test to "MyStringSpecTest.kt",
            test to "MyFunSpecTest.kt",
            test to "MyFreeSpecTest.kt",
            test to "MyWordSpecTest.kt",
            test to "MyBehaviorSpecTest.kt",
            test to "MyFeatureSpecTest.kt",
        )
    }
}
