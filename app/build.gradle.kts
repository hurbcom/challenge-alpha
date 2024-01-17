plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.wesleyerick.podracer"
    compileSdk = 31

    defaultConfig {
        applicationId = "com.wesleyerick.podracer"
        minSdk = 23
        //noinspection OldTargetApi
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        debug {
            buildConfigField("String", "BASE_URL", "\"https://swapi.dev/api/\"")
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0")
    val ktxVersion = "1.3.1"
    val constraintLayoutVersion = "2.0.1"
    val appCompatVersion = "1.3.1"
    val materialVersion = "1.2.1"
    val retrofitVersion = "2.9.0"
    val koinVersion = "3.2.2"
    val glideVersion = "4.15.1"

    val jUnitVersion = "4.13.2"
    val jUnitTestVersion = "1.1.2"
    val mockkVersion = "1.12.4"
    val coroutinesTestVersion = "1.6.1"
    val espressoVersion = "3.3.0"

    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("androidx.core:core-ktx:$ktxVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.appcompat:appcompat-resources:$appCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    testImplementation("junit:junit:$jUnitVersion")
    androidTestImplementation("androidx.test.ext:junit:$jUnitTestVersion")
    testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
}
