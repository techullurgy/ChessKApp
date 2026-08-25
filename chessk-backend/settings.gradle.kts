rootProject.name = "chessk-backend"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("server") {
            from(files("../gradle/server.versions.toml"))
        }
        create("common") {
            from(files("../gradle/common.versions.toml"))
        }
    }

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

// include(":common")
// include(":websocket-service")
