rootProject.name = "chesskapp"

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    includeBuild("../chessk-common") {
        dependencySubstitution {
            substitute(module("com.techullurgy.chessk:chessk-common"))
                .using(project(":"))
        }
    }

    versionCatalogs {
        create("app") {
            from(files("../gradle/app.versions.toml"))
        }
        create("project") {
            from(files("../gradle/project.versions.toml"))
        }
        create("common") {
            from(files("../gradle/common.versions.toml"))
        }
    }

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

//include(":androidApp")
include(":desktopApp")

include(":shared")