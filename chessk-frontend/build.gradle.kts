plugins {
    alias(app.plugins.kotlinMultiplatform) apply false
    alias(app.plugins.composeMultiplatform) apply false
    alias(app.plugins.androidKmpLibrary) apply false
    alias(app.plugins.composeCompiler) apply false
    alias(app.plugins.androidApplication) apply false
    alias(app.plugins.kotlin.jvm) apply false
    alias(app.plugins.kotlin.serialization) apply false

    alias(project.plugins.conventions.kmp.library) apply false
}