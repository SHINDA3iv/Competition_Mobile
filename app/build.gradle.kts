plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.parkingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.parkingapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {
    val versionRetrofit = "2.9.0"
    val versionOkHttp = "4.7.2"
    val versionLifecycle = "2.7.0"
    val versionCoroutines = "1.7.1"
    val versionMoshi = "1.13.0"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$versionLifecycle")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$versionLifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$versionLifecycle")

    //Retrofit - Работа с сетью
    implementation("com.squareup.retrofit2:retrofit:$versionRetrofit")
    implementation("com.squareup.retrofit2:retrofit-mock:$versionRetrofit")
    implementation("com.squareup.retrofit2:converter-gson:$versionRetrofit")
    implementation("com.squareup.okhttp3:okhttp:$versionOkHttp")
    implementation("com.squareup.okhttp3:logging-interceptor:$versionOkHttp")

    //Coroutines - Асинхронное программирование
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$versionCoroutines")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$versionCoroutines")

    // Moshi - Обработка json
    implementation ("com.squareup.moshi:moshi:$versionMoshi")
    implementation ("com.squareup.moshi:moshi-kotlin:$versionMoshi")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
}