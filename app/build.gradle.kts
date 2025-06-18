plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.kapt) // Kotlin KAPT
    alias(libs.plugins.hilt.android) // HILT Plugin
}

android {
    namespace = "com.example.reviewrecipesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.reviewrecipesapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Compose Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material)
    implementation(libs.ui)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)


    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)


    //okHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)


    //Gson
    implementation(libs.gson)


    //Coroutines
    implementation(libs.kotlinx.coroutines.android)


    //HILT Imports
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    //HILTViewModels
    implementation(libs.androidx.hilt.navigation.fragment)
    runtimeOnly(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose.v100)


    //Image
    implementation(libs.coil.compose)
    implementation(libs.material)
    implementation(libs.roundedimageview)

    //test coroutine
    testImplementation(libs.kotlinx.coroutines.test)

    //LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.runtime.livedata)

    //ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Testing Imports
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Mockk for Unit Tests
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("io.mockk:mockk-agent-jvm:1.13.8") // Required for mocking final classes

    // Coroutines Test (You already have this, but just to confirm)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Turbine (For testing Flows)
    testImplementation("app.cash.turbine:turbine:1.0.0")
}