import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupBiggestModule") {
    description = "Creates the base module and files for the testing introductory lesson"
    module.set("pbt:biggest")
}
