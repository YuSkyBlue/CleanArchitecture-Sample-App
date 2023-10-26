@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) //    id("com.android.application")
    alias(libs.plugins.kotlin.android) //    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ckyu.cleanarchitecture_sample_app"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ckyu.cleanarchitecture_sample_app"
        minSdk =  libs.versions.minSdk.get().toInt() // 24
        targetSdk = libs.versions.targetSdk.get().toInt() // 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        /*release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }*/
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            testProguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguardTest-rules.pro"
            )
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            testProguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguardTest-rules.pro"
            )
        }
    }
    // Always show the result of every unit test, even if it passes.
    testOptions.unitTests {
        isIncludeAndroidResources = true

        all { test ->
            with(test){
                testLogging {
                    events = setOf(
                        org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
                    )
                }
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =  libs.versions.androidxComposeCompiler.get() //"1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // App dependencies
    implementation(libs.androidx.annotation)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.timber)
//    implementation(libs.androidx.test.espresso.idling.resources)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose) // "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    implementation(libs.androidx.activity.compose) // "androidx.activity:activity-compose:1.7.2"

    //jetpack Compose
    val composeBom = platform(libs.androidx.compose.bom) //    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation(composeBom)

//    implementation("androidx.compose.ui:ui")
    implementation(libs.androidx.compose.ui.tooling.preview)  //"androidx.compose.ui:ui-tooling-preview"

    // Hilt
    implementation(libs.hilt.android.core)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    // JVM tests - Hilt
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.compiler)
    // AndroidX Test - Hilt testing
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)

    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit4) // "junit:junit:4.13.2"
    androidTestImplementation(libs.androidx.test.ext) // "androidx.test.ext:junit:1.1.5
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // "androidx.test.espresso:espresso-core:3.5.1"
    androidTestImplementation(composeBom) // platform("androidx.compose:compose-bom:2023.03.00")
    androidTestImplementation(libs.androidx.compose.ui.test.junit)  // "androidx.compose.ui:ui-test-junit4"
    debugImplementation(libs.androidx.compose.ui.tooling.core) // "androidx.compose.ui:ui-tooling"
    debugImplementation(libs.androidx.compose.ui.test.manifest) // "androidx.compose.ui:ui-test-manifest"
}