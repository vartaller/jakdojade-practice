plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    namespace = "com.volodymyr.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.volodymyr.myapplication"
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
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packagingOptions {
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
    }
}

android.applicationVariants.all {
    val variantName = name
    kotlin.sourceSets {
        getByName("main") {
            kotlin.srcDir(File("build/generated/ksp/$variantName/kotlin"))
        }
    }
}

android.buildTypes.all {
    val variantName = name
    kotlin.sourceSets {
        getByName("main") {
            kotlin.srcDir(File("build/generated/ksp/$variantName/kotlin"))
        }
    }
}

dependencies {
    implementation(project(":data"))
    val roomVersion = "2.6.1"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
    implementation(project(":common:provider"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation(project(mapOf("path" to ":features:ticket")))
    implementation(project(mapOf("path" to ":features:list")))
    implementation(project(mapOf("path" to ":common:ui-theme")))

    implementation("io.github.raamcosta.compose-destinations:core:1.3.1-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.3.1-beta")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("androidx.appcompat:appcompat:$rootProject.appCompatVersion")
    implementation("androidx.activity:activity-ktx:$rootProject.activityVersion")

    // Room components
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$rootProject.lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$rootProject.lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$rootProject.lifecycleVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.6")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$rootProject.coroutines")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$rootProject.coroutines")
    implementation("androidx.constraintlayout:constraintlayout:$rootProject.constraintLayoutVersion")
    implementation("com.google.android.material:material:$rootProject.materialVersion")

    testImplementation("junit:junit:$rootProject.junitVersion")
    androidTestImplementation("androidx.arch.core:core-testing:$rootProject.coreTestingVersion")
    androidTestImplementation ("androidx.test.espresso:espresso-core:$rootProject.espressoVersion",
//        {
//        exclude group: 'com.android.support', module: 'support-annotations'
//    }
    )
    androidTestImplementation("androidx.test.ext:junit:$rootProject.androidxJunitVersion")
}
kapt {
    correctErrorTypes = true
}