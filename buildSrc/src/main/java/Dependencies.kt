object App {
    const val name = "CarCare"
    const val application = "br.com.joaogoes.carcare"
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "4.1.0-alpha07"
    const val kotlin = "1.3.71"
    const val appcompat = "1.1.0"
    const val compose = "0.1.0-dev09"
    const val material = "1.1.0"
    const val coroutines = "1.3.5"
    const val koin = "2.0.1"
    const val room = "2.2.4"
}

object TestVersions {
    const val junit = "4.12"
    const val mockk = "1.9.3"
    const val extJunit = "1.1.1"
    const val espresso = "3.2.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val database = ":database"
    const val ui = ":ui"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val koin = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val core = "androidx.core:core-ktx:1.2.0"
    }

    object Corountines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Ui {
        private const val composePrefix = "androidx.ui"
        const val framework = "$composePrefix:ui-framework:${Versions.compose}"
        const val layout = "$composePrefix:ui-layout:${Versions.compose}"
        const val foundation = "$composePrefix:ui-foundation:${Versions.compose}"
        const val material = "$composePrefix:ui-material:${Versions.compose}"
        const val tooling = "$composePrefix:ui-tooling:${Versions.compose}"
        const val livedata = "$composePrefix:ui-livedata:${Versions.compose}"
        const val compiler = "androidx.compose:compose-compiler:${Versions.compose}"
        const val runtime = "androidx.compose:compose-runtime:${Versions.compose}"
    }
}

object TestLib {
    const val junit = "junit:junit:${TestVersions.junit}"
    const val mockk = "io.mockk:mockk:${TestVersions.mockk}"
    const val room = "androidx.room:room-testing:${Versions.room}"
    const val extJunit = "androidx.test.ext:junit:${TestVersions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"
}