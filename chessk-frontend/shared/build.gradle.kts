plugins {
    alias(project.plugins.conventions.kmp.library)
}

kmpConvention {
    android {
        localNamespace = "shared"
    }

    ios {
        enabled.set(false)
    }

    jvm {}
    js { enabled = true }
    wasm { enabled = true }

    compose {
        enabled = true
        foundation = true
        ui = true
        preview = true
        resources = true
        material3 = true
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(app.compose.material3.adaptive)
        }
    }
}