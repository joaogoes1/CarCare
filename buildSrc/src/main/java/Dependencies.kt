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
    const val gradle = "7.0.0-alpha04"
<<<<<<< HEAD
    const val kotlin = "1.4.32"
    const val appcompat = "1.1.0"
    const val compose = "1.0.0-beta07"
=======
    const val kotlin = "1.4.21"
    const val appcompat = "1.1.0"
    const val compose = "1.0.0-alpha10"
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
    const val material = "1.1.0"
    const val coroutines = "1.3.5"
    const val koin = "3.0.2"
    const val room = "2.2.4"
    const val lifecycle = "2.2.0"
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

    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val core = "androidx.core:core-ktx:1.2.0"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object Ui {
        private const val uiPrefix = "androidx.compose.ui"
        private const val foundationPrefix = "androidx.compose.foundation"
        private const val runtimePrefix = "androidx.compose.runtime"
        private const val materialPrefix = "androidx.compose.material"
        const val framework = "$uiPrefix:ui:${Versions.compose}"
        const val foundation = "$foundationPrefix:foundation:${Versions.compose}"
        const val material = "$materialPrefix:material:${Versions.compose}"
        const val tooling = "$uiPrefix:ui-tooling:${Versions.compose}"
        const val runtime = "$runtimePrefix:runtime:${Versions.compose}"
        const val livedata = "$runtimePrefix:runtime-livedata:${Versions.compose}"
        const val compiler = "androidx.compose:compose-compiler:${Versions.compose}"
    }
}

object TestLib {
    const val junit = "junit:junit:${TestVersions.junit}"
    const val mockk = "io.mockk:mockk:${TestVersions.mockk}"
    const val room = "androidx.room:room-testing:${Versions.room}"
    const val extJunit = "androidx.test.ext:junit:${TestVersions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"
}