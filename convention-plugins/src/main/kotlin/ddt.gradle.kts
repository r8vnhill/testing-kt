import tasks.ModuleSetupTask

tasks.register<ModuleSetupTask>("setupDdtModule") {
    description = "Creates the base module and files for the data driven testing lesson"
    module.set("ddt")

    doLast {
        createFiles(
            "password",
            test to "PasswordValidatorTest.kt",
            main to "PasswordValidator.kt",
        )
        createFiles(
            "phone",
            test to "PhoneNumberValidatorTest.kt",
            main to "PhoneNumberValidator.kt",
        )
    }
}
