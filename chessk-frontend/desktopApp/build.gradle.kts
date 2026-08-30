import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(app.plugins.kotlin.jvm)
    alias(app.plugins.composeMultiplatform)
    alias(app.plugins.composeCompiler)
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
    implementation(app.kotlinx.coroutines.swing)

    implementation(app.compose.ui.tooling.preview)
}

compose.desktop {
    application {
        mainClass = "com.techullurgy.chesskapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.techullurgy.chesskapp"
            packageVersion = "1.0.0"
        }
    }
}