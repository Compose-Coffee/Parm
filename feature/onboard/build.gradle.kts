plugins {
    alias(libs.plugins.nbdream.android.feature)
    alias(libs.plugins.nbdream.android.library.compose)
    alias(libs.plugins.sgp)
}

android {
    namespace = "kr.co.onboard"

    defaultConfig {
        buildConfigField("String", "KAKAO_API_KEY", "\"kakaoApiKey\"")
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.kakao.maps)
    implementation (libs.accompanist.navigation.animation)
}
