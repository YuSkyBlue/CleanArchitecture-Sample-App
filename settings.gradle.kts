pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    /*versionCatalogs {
        create("libs") {
            from(files("libs.version.toml"))
        }
    }*/
}

rootProject.name = "CleanArchitecture-Sample-App"
include(":app")
 