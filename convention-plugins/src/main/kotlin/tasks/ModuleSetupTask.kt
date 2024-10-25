package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File

private val printError: (String) -> Unit = System.err::println

abstract class ModuleSetupTask : DefaultTask() {

    @get:Input
    abstract val module: Property<String>

    @get:Internal
    val main: File
        get() = baseDir.resolve("src/main/kotlin")

    @get:Internal
    val test: File
        get() = baseDir.resolve("src/test/kotlin")

    private val baseDir: File
        get() = project.file(module.get().replace(":", "/"))

    init {
        group = "setup"
    }

    @TaskAction
    fun setupSubmodule() {
        createSettingsFile()
        createModuleDirectory()
        createBuildFile()
    }

    private fun createSettingsFile() {
        val settingsFile = project.file("settings.gradle.kts")
        if (!settingsFile.exists()) {
            settingsFile.writeText("rootProject.name = \"${project.name}\"\n")
            settingsFile.writeText("include(\"${module.get()}\")")
        } else if (module.get() !in settingsFile.readText()) {
            settingsFile.appendText("\ninclude(\"${module.get()}\")")
        }
        println("The module ${module.get()} was added to the settings file")
    }


    private fun createModuleDirectory() = baseDir.run {
        when {
            exists() -> printError("Directory already exists: $absolutePath")
            mkdirs() -> println("Directory created: $absolutePath")
            else -> printError("Failed to create directory: $absolutePath")
        }
    }

    private fun createBuildFile() = baseDir.resolve("build.gradle.kts").run {
        if (exists()) printError("The build file already exists")
        else {
            writeText("// Intentionally left blank\n")
            println("The build file was created successfully")
        }
    }

    fun createFiles(packageName: String, vararg files: Pair<File, String>) {
        files.forEach { (dir, name) ->
            val group = project.rootProject.group.toString()
            val packageDir = dir.resolve("$group/$packageName".replace(".", "/"))
            val file = packageDir.resolve(name)
            if (file.exists()) {
                printError("The file $name already exists")
            } else {
                packageDir.mkdirs()
                file.writeText("package $group.$packageName\n\n")
                println("The file $name was created successfully")
            }
        }
    }
}
