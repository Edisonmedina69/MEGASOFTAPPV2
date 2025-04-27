plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    // Si usas Crashlytics:
    // id("com.google.firebase.crashlytics")
}

android {
    namespace = "py.edison.megasoftappv2"
    compileSdk = 34

    defaultConfig {
        applicationId = "py.edison.megasoftappv2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-database-ktx")

    implementation ("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-auth")
    implementation("junit:junit:4.12")
    implementation("androidx.test.ext:junit:1.2.1")
    implementation ("com.google.android.material:material:1.6.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // Material Design
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")


}
