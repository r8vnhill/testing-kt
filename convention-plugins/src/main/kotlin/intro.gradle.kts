import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupIntroModule") {
    description = "Creates the base module and files for the testing introductory lesson"
    module.set("intro")
}
