import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(app.plugins.androidApplication)
    alias(app.plugins.composeCompiler)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(app.androidx.activity.compose)

    implementation(app.compose.ui.tooling.preview)
    debugImplementation(app.compose.ui.tooling)
}

android {
    namespace = "com.techullurgy.chesskapp"
    compileSdk = app.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.techullurgy.chesskapp"
        minSdk = app.versions.android.minSdk.get().toInt()
        targetSdk = app.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }
}