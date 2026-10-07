// digunakan untuk mendaftarkan modul pustaka/pembangun bawaan maupun pihak ketiga
// yang memperluas kemampuan proses kompilasi Gradle.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

// inti pengaturan SDK, identitas aplikasi, arsitektur build, serta opsi pengkompilasian.
android {
    namespace = "com.example.mobileadvapp"
    compileSdk = 35

    // Mengatur atribut dasar aplikasi seperti `applicationId` (ID unik aplikasi di
    // Google Play Store), `minSdk` (versi Android minimum yang didukung), `targetSdk` (versi Android
    // tempat aplikasi diuji optimal), serta versi rilis (`versionCode` & `versionName`).
    defaultConfig {
        applicationId = "com.example.mobileadvapp"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    // Mengaktifkan atau menonaktifkan fitur khusus platform Android. Mengatur
    // `viewBinding = true` akan merintis generasi otomatis kelas binding untuk tiap berkas XML layout
    // secara type-safe dan null-safe.
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // `implementation(...)`: Mendaftarkan pustaka ke dalam classpath kompilasi dan runtime modul.
    // Library ini dikemas ke dalam APK akhir tetapi tidak diekspos secara transitif ke modul lain.
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    // `testImplementation(...)`: Pustaka yang hanya disertakan saat menjalankan Unit Test lokal di
    // mesin pengembang (JVM).
    testImplementation(libs.junit)
    // `androidTestImplementation(...)`: Pustaka khusus untuk pengujian instrumentasi UI yang berjalan
    // langsung di atas perangkat fisik atau emulator Android.
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}