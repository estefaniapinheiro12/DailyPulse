plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    // fix for issue with "platform" in Platform.ios.kt
    id("com.louiscad.complete-kotlin") version "1.1.0"
}

android {
    namespace = "com.estefaniapinheiro.dailypulse.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.estefaniapinheiro.dailypulse.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "META-INF/versions/9/previous-compilation-data.bin"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    // implementação da biblioteca coil
    implementation(libs.coil.compose)
    // Navigation Compose
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.runtime.ktx)

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation (libs.accompanist.swiperefresh)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.accompanist.swiperefresh)

}