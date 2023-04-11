plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "br.com.vaniala.starwars"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "br.com.vaniala.starwars"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://swapi.dev/api/\"")
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            buildConfigField("String", "BASE_URL", "\"https://swapi.dev/api/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    viewBinding.isEnabled = true

    tasks {
        val path = "../../../.git/hooks"
        val origin = "${rootProject.rootDir}/scripts/pre-commit"
        register("gitHooks", Copy::class) {
            from(origin)
            into(path)
            eachFile {
                fileMode = 0b111101101
            }
        }
        afterEvaluate {
            tasks["preBuild"].dependsOn(tasks.named("gitHooks"))
        }
    }
    tasks.withType<Test> {
        testLogging {
            showStandardStreams = true
            events("passed", "failed")
        }
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.navigation)

    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    debugImplementation(libs.leakcanary)

    implementation(libs.shimmer)

    implementation(libs.timber)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso)

    testImplementation(libs.coroutines)
    testImplementation(libs.mockk)
}
