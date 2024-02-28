plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.volodymyr.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    val roomVersion = "2.6.1"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.appcompat:appcompat:$rootProject.appCompatVersion")
    implementation("androidx.activity:activity-ktx:$rootProject.activityVersion")

    // Room components
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-runtime:2.4.0")
    kapt("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

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