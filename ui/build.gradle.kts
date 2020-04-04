plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}
android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionCode(App.versionCode)
        versionName(App.versionName)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.3.61-dev-withExperimentalGoogleExtensions-20200129"
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.Androidx.core)
    implementation(Libs.Androidx.appcompat)
    implementation(Libs.Ui.framework)
    implementation(Libs.Ui.layout)
    implementation(Libs.Ui.foundation)
    implementation(Libs.Ui.material)
    implementation(Libs.Ui.tooling)
    implementation(Libs.material)
    implementation(project(Modules.core))

    testImplementation(TestLib.junit)
    androidTestImplementation(TestLib.extJunit)
    androidTestImplementation(TestLib.espresso)
}
