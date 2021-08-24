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
    api(Libs.Coroutines.core)
    api(Libs.Coroutines.android)
    testImplementation(TestLib.junit)
    testImplementation(TestLib.mockk)
}