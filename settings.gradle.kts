pluginManagement {
    includeBuild("build-src")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }

        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/repository/kakaomap-releases/") }

        maven { url = java.net.URI("https://naver.github.io/checkout-sdk/android")}
    }
}

rootProject.name = "NBDream"

include(":app")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":core:domain")
include(":core:data")
include(":core:common")
include(":core:ui")
include(":core:local")
include(":core:remote")
include(":core:oauth")

include(":feature:onboard")
include(":feature:main")
