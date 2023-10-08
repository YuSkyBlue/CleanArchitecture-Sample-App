// Top-level build file where you can add configuration options common to all sub-projects/modules.

@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply  false  //    id("com.android.application") version "8.1.1" apply false
    alias(libs.plugins.kotlin.android) apply false  //   id("org.jetbrains.kotlin.android") version "1.8.10" apply false

}