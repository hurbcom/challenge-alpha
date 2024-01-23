plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.br.myapplication"
    compileSdk = 34

    android.buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "com.br.myapplication"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug {
            isDebuggable = true
        }

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
        jvmTarget= "1.8"
    }

    buildFeatures {

        dataBinding = true
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }

    flavorDimensions += "normal"

    productFlavors {
        create("mock") {
            versionNameSuffix = "-MOCK"
            dimension = "normal"
            buildConfigField("String", "BASE_URL", "\"https://swapi.dev/api/\"")
            buildConfigField("Boolean", "IS_MOCK", "true")
        }

        create("dev") {
            versionNameSuffix = "-DEV"
            dimension = "normal"
            buildConfigField("String", "BASE_URL" , "\"https://swapi.dev/api/\"")
            buildConfigField("Boolean", "IS_MOCK" , "false")
        }

        create("prd") {
            versionNameSuffix = "-PRD"
            dimension = "normal"
            buildConfigField("String", "BASE_URL" , "\"https://swapi.dev/api/\"")
            buildConfigField("Boolean", "IS_MOCK" , "false")
        }
    }

}

dependencies {

    val koinVersion = "3.5.3"
    val pagingVersion = "3.2.1"

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.1")
    implementation("com.squareup.moshi:moshi:1.11.0")

    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("io.insert-koin:koin-test-junit4:$koinVersion")
    // Koin for JUnit 5
    androidTestImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
}