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
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.4.21"
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.material)
    implementation(Libs.Lifecycle.viewModel)
    implementation(Libs.Androidx.core)
    implementation(Libs.Androidx.appcompat)
    implementation(Libs.Ui.framework)
    implementation(Libs.Ui.foundation)
    implementation(Libs.Ui.material)
    implementation(Libs.Ui.tooling)
    implementation(Libs.Ui.livedata)
    implementation(Libs.Ui.runtime)
//    kotlin(Libs.Ui.compiler)
    implementation(project(Modules.core))

    testImplementation(TestLib.junit)
    androidTestImplementation(TestLib.extJunit)
    androidTestImplementation(TestLib.espresso)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies")
    }
}