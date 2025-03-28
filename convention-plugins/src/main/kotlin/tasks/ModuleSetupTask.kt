package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

/**
 * Abstract Gradle task for setting up a new submodule in a multi-module project.
 *
 * This task automates the creation of directory structures, build files, and settings references required to add a new
 * module to a Gradle project. It is designed to work with Kotlin DSL.
 *
 * ## Behavior:
 * - Adds the module to `settings.gradle.kts` (if not already included).
 * - Creates the source directories (`src/main/kotlin` and `src/test/kotlin`).
 * - Creates an empty `build.gradle.kts` file.
 *
 * ## Usage:
 * Implement a concrete subclass of this task and configure the `module` property before execution.
 *
 * ### Example:
 * ```kotlin
 * tasks.register<CustomSetupTask>("setupMyModule") {
 *     module.set(":my-new-module")
 * }
 * ```
 *
 * @property module The Gradle path of the module to be created (e.g., `":my-module"`).
 * @property layout The project layout used to resolve file paths.
 */
abstract class ModuleSetupTask @Inject constructor(
    private val layout: ProjectLayout
) : DefaultTask() {

    // region Captured metadata from the current project

    private val capturedProjectName: String = project.name
    private val capturedGroup: String = project.rootProject.group.toString()

    // endregion

    // region Inputs

    @get:Input
    abstract val module: Property<String>

    // endregion

    // region Output directories

    @get:Internal
    val main: File
        get() = baseDir.resolve("src/main/kotlin")

    @get:Internal
    val test: File
        get() = baseDir.resolve("src/test/kotlin")

    private val baseDir: File
        get() = layout.projectDirectory.file(module.get().replace(":", "/")).asFile

    // endregion

    init {
        group = "setup"
    }

    // region Task action

    /**
     * Main entry point when the task is executed.
     *
     * Creates the settings entry, directory structure, and an empty build file for the module.
     */
    @TaskAction
    fun setupSubmodule() {
        createSettingsFile()
        createModuleDirectory()
        createBuildFile()
    }

    // endregion

    // region Internal helper functions

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

    /**
     * Creates source files under the given package inside the provided directories.
     *
     * @param packageName The subpackage (e.g., `"setup"`).
     * @param files Pairs of directory and filename where the files will be created.
     */
    fun createFiles(packageName: String, vararg files: Pair<File, String>) =
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

    private fun printError(message: String) = logger.error(message)

    // endregion
}
