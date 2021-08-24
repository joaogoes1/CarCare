plugins {
    id("com.android.library")
    id("kotlin-android")
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.material)
    implementation(Libs.Lifecycle.viewModel)
    implementation(Libs.Androidx.core)
    implementation(Libs.Androidx.appcompat)
    implementation("androidx.activity:activity-compose:1.3.0-alpha08")
    implementation(Libs.Ui.framework)
    implementation(Libs.Ui.foundation)
    implementation(Libs.Ui.material)
    implementation(Libs.Ui.tooling)
    implementation(Libs.Ui.livedata)
    implementation(Libs.Ui.runtime)
    implementation(Libs.Koin.core)
//    implementation(Libs.Koin.compose)
//    kotlin(Libs.Ui.compiler)
    implementation(project(Modules.core))

    testImplementation(TestLib.junit)
    androidTestImplementation(TestLib.extJunit)
    androidTestImplementation(TestLib.espresso)
}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions {
//        jvmTarget = "1.8"
//        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies")
//    }
//}