import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupPredefMatchersModule") {
    description = "Creates the base module and files for the predefined matchers module."
    module.set("matchers:predef")
    doLast {

    }
}