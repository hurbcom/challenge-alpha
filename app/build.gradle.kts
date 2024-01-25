plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.wesleyerick.podracer"
    compileSdk = 31

    packaging {
        resources.excludes.add("META-INF/*")
    }

    defaultConfig {
        applicationId = "com.wesleyerick.podracer"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
            buildConfigField(
                "String",
                "BASE_IMAGE_URL",
                "\"https://starwars-visualguide.com/assets/img/\""
            )
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        debug {
            buildConfigField("String", "BASE_URL", "\"https://swapi.dev/api/\"")
            buildConfigField(
                "String",
                "BASE_IMAGE_URL",
                "\"https://starwars-visualguide.com/assets/img/\""
            )
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

//noinspection GradleDependency
dependencies {
    // Jetpack Compose
    val composeVersion = "1.0.0"
    val composeConstraintVersion = "1.0.1"
    val composeCoilVersion = "1.3.2"

    //Tests
    val jUnitVersion = "4.13.2"
    val jUnitTestVersion = "1.1.5"
    val mockkVersion = "1.12.4"
    val coroutinesTestVersion = "1.6.1"
    val espressoVersion = "3.3.0"

    val ktxVersion = "1.3.1"
    val constraintLayoutVersion = "2.1.4"
    val appCompatVersion = "1.3.1"
    val materialVersion = "1.2.1"
    val retrofitVersion = "2.9.0"
    val koinVersion = "3.2.2"
    val glideVersion = "4.15.1"
    val navigationFragmentVersion = "2.5.3"
    val legacySupportVersion = "1.0.0"
    val lifecycleLivedataVersion = "2.1.0"
    val lifecycleViewmodelVersion = "2.1.0"

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("io.coil-kt:coil-compose:$composeCoilVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:$composeConstraintVersion")

    // Koin
    implementation("io.insert-koin:koin-android:$koinVersion")
    testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")

    // Tests
    testImplementation("junit:junit:$jUnitVersion")
    androidTestImplementation("androidx.test.ext:junit:$jUnitTestVersion")
    testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")

    // Other Dependencies
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("androidx.core:core-ktx:$ktxVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.appcompat:appcompat-resources:$appCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationFragmentVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationFragmentVersion")
    implementation("androidx.legacy:legacy-support-v4:$legacySupportVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleLivedataVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewmodelVersion")
}
