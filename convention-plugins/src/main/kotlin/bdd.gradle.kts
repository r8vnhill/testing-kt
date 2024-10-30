import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupdBddModule") {
    description = "Creates the base module and files for the BDD introductory lesson"
    module.set("bdd")
    doLast {
        createFiles(
            "users",
            main to "UserService.kt",
            test to "UserServiceTest.kt",
        )
    }
}