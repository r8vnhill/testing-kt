import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupStrCatModule") {
    description = "Creates the base module and files for the string concatenation properties lesson"
    module.set("pbt:str-cat")

    doLast {
        createFiles(
            packageName = "string",
            test to "StringConcatenationLengthTest.kt",
            test to "StringConcatenationMonoidTest.kt",
        )
    }
}
