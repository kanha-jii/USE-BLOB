import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.useblob"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.useblob"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packaging {
        resources.excludes.add("META-INF/INDEX.LIST");
        resources.excludes.add("META-INF/NOTICE.md");
        resources.excludes.add("META-INF/io.netty.versions.properties");
        resources.excludes.add("META-INF/LICENSE.md");
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.azure:azure-storage-blob:12.14.0")
//    {
//        exclude(group = "io.netty', module: 'netty-transport-native-epoll")
//        exclude(group = "io.netty', module: 'netty-transport-native-kqueue")
//    }
//    implementation("com.microsoft.azure.android:azure-storage-android:2.0.0@aar")
    implementation("androidx.multidex:multidex:2.0.1")

//    implementation ("javax.xml.stream:stax-api:1.0-2")
//    implementation ("com.fasterxml.woodstox:woodstox-core:6.2.4")
//    implementation ("org.codehaus.woodstox:stax2-api:4.2.1")

//    implementation ("org.slf4j:slf4j-api:1.7.30")
//    implementation ("org.slf4j:slf4j-simple:1.7.30")

//    implementation ("io.grpc:grpc-okhttp:1.7.0")
//    implementation ("com.squareup.okhttp3:okhttp:3.0.1")

    // Netty dependencies
//    implementation ("io.netty:netty-all:4.1.65.Final")
//    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.1")
    implementation ("javax.xml.stream:stax-api:1.0")
}