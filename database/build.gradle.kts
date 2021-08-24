plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
}
android {
    compileSdk = App.compileSdk
    defaultConfig {
        minSdk = App.minSdk
        targetSdk = App.targetSdk

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
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.Androidx.core)
    implementation(Libs.Androidx.appcompat)
    implementation(Libs.Koin.core)
    api(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    kapt(Libs.Room.compiler)
    implementation(project(Modules.core))

    testImplementation(TestLib.room)
    testImplementation(TestLib.junit)
    androidTestImplementation(TestLib.extJunit)
    androidTestImplementation(TestLib.espresso)
}