plugins {
    id("java-library")
    id("kotlin")
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Libs.kotlin)
    api(Libs.Corountines.core)
    api(Libs.Corountines.android)
    api(Libs.koin)
    testImplementation(TestLib.junit)
    testImplementation(TestLib.mockk)
}