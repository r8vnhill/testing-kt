package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class ModuleSetupTask : DefaultTask() {

    @get:Input
    abstract var module: Property<String>

    init {
        group = "setup"
    }

    @TaskAction
    fun setupSubmodule() {
        createSettingsFile()
    }

    private fun createSettingsFile() {
        val settingsFile = project.file("settings.gradle.kts")
        if (!settingsFile.exists()) {
            settingsFile.writeText("include(\"$module\")")
        } else if (module.get() !in settingsFile.readText()) {
            settingsFile.appendText("\ninclude(\"$module\")")
        }
    }
}
