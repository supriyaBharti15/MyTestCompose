plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dagger.hilt.android)
    //kotlin("plugin.serialization") version "2.0.21"
    id ("org.jetbrains.kotlin.plugin.serialization")
    id ("kotlinx-serialization")
}

android {
    namespace = "com.example.mytestcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mytestcompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    //coroutines
    implementation(libs.androidx.coroutines.core)
    implementation(libs.androidx.coroutines.android)
    // Retrofit
    implementation(libs.androidx.retrofit2.converter.gson)
    implementation(libs.androidx.retrofit2)
    implementation(libs.androidx.okhttp3.logging.interceptor)

    //view model for compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")

    //navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    val nav_version = "2.8.1"
    implementation("androidx.navigation:navigation-compose:$nav_version")


    //hilt
    implementation(libs.androidx.dagger.hilt.android)
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation("androidx.compose.runtime:runtime:1.7.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.3")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.7.3")
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation ("com.google.code.gson:gson:2.8.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    //datastore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    //splash screen
    implementation("androidx.core:core-splashscreen:1.0.0")

}