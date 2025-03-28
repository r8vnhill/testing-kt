package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

private val printError: (String) -> Unit = System.err::println

abstract class ModuleSetupTask @Inject constructor(
    private val layout: ProjectLayout
) : DefaultTask() {

    private val capturedProjectName: String = project.name

    private val capturedGroup: String = project.rootProject.group.toString()

    @get:Input
    abstract val module: Property<String>

    @get:Internal
    val main: File
        get() = baseDir.resolve("src/main/kotlin")

    @get:Internal
    val test: File
        get() = baseDir.resolve("src/test/kotlin")

    private val baseDir: File
        get() = layout.projectDirectory.file(module.get().replace(":", "/")).asFile

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
        val settingsFile: File = layout.projectDirectory.file("settings.gradle.kts").asFile
        if (!settingsFile.exists()) {
            settingsFile.writeText(
                """
                rootProject.name = "$capturedProjectName"
                include("${module.get()}")
                """.trimIndent()
            )
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
            val packageDir = dir.resolve("$capturedGroup/$packageName".replace(".", "/"))
            val file = packageDir.resolve(name)
            if (file.exists()) {
                printError("The file $name already exists")
            } else {
                packageDir.mkdirs()
                file.writeText("package $capturedGroup.$packageName\n\n")
                println("The file $name was created successfully")
            }
        }
    }

    private fun printError(message: String) {
        logger.error(message)
    }
}
