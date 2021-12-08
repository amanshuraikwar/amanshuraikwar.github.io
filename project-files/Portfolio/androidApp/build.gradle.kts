import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import io.github.amanshuraikwar.nxtbuz.buildSrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.github.amanshuraikwar.portfolio"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
        }
        debug {
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    applicationVariants.all {
        outputs.all {
            (this as BaseVariantOutputImpl).outputFileName =
                "Portfolio-$versionName-($versionCode)-${buildType.name}.apk"
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
        buildConfig = true
        resValues = true

        // Disable unused AGP features
        aidl = false
        renderScript = false
        shaders = false
        dataBinding = false
        viewBinding = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(Libs.Kotlin.stdlib)

    with(Libs.Coroutines) {
        implementation(core)
        implementation(android)
    }

    with(Libs.Google) {
        implementation(material)
    }

    with(Libs.AndroidX.Lifecycle) {
        implementation(viewmodel)
    }

    Libs.AndroidX.Compose.all.forEach { dependency ->
        implementation(dependency)
    }

    with(Libs.Accompanist) {
        implementation(insets)
    }

    implementation(Libs.coilCompose)
}