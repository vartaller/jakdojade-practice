// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.8.20-1.0.11" apply false
}