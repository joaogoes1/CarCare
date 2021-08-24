plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}
android {
    compileSdk = App.compileSdk
    defaultConfig {
        applicationId = App.application
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.Androidx.core)
    implementation(Libs.Androidx.appcompat)
    implementation(Libs.Koin.core)
    implementation(project(Modules.database))
    implementation(project(Modules.ui))
    implementation(project(Modules.core))
    testImplementation(TestLib.junit)
    androidTestImplementation(TestLib.extJunit)
    androidTestImplementation(TestLib.espresso)
}