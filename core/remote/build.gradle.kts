import kr.co.wdtt.convention.Remote

plugins {
    alias(libs.plugins.nbdream.android.library)
    alias(libs.plugins.nbdream.android.hilt)
    alias(libs.plugins.sgp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "kr.co.nbdream.core.remote"

    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    flavorDimensions.add("environment")
    productFlavors {
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"http://34.47.73.18/\"")
        }

        create("prod") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"http://34.47.73.18/\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)

    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    Remote()
}