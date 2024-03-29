@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.github.amanshuraikwar.nxtbuz.buildSrc

object Libs {
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.2.4"

    const val multiplatformSettings = "com.russhwolf:multiplatform-settings:0.8.1"
    const val multiplatformSettingsNoArg = "com.russhwolf:multiplatform-settings-no-arg:0.8.1"
    const val multiplatformSettingsTest = "com.russhwolf:multiplatform-settings-test:0.8.1"

    const val coilCompose = "io.coil-kt:coil-compose:1.3.1"

    object Ktor {
        private const val version = "2.0.0-beta-1"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val clientCore = "io.ktor:ktor-client-core:$version"
        const val clientJson = "io.ktor:ktor-client-json:$version"
        const val clientLogging = "io.ktor:ktor-client-logging:$version"
        const val clientSerialization = "io.ktor:ktor-client-serialization:$version"
        const val clientAndroid = "io.ktor:ktor-client-android:$version"
        const val clientApache = "io.ktor:ktor-client-apache:$version"
        const val clientIos = "io.ktor:ktor-client-ios:$version"
        const val clientCio = "io.ktor:ktor-client-cio:$version"
        const val clientJs = "io.ktor:ktor-client-js:$version"
        const val clientMock = "io.ktor:ktor-client-mock:$version"
    }

    object Kotlinx {
        private const val version = "1.3.2"
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:$version"
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
    }

    object Google {
        const val material = "com.google.android.material:material:1.4.0-rc01"
        const val analytics = "com.google.firebase:firebase-analytics:17.4.0"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:17.1.1"
        const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.5.2"
        const val gmsGoogleServices = "com.google.gms:google-services:4.3.3"
        const val playServicesMap = "com.google.android.gms:play-services-maps:17.0.0"
        const val playServicesLocation = "com.google.android.gms:play-services-location:17.0.0"
        const val gmsOssLicenses = "com.google.android.gms:oss-licenses-plugin:0.10.4"
        const val playOssLicenses = "com.google.android.gms:play-services-oss-licenses:17.0.0"
        const val playCore = "com.google.android.play:core:1.10.0"
        const val playCoreKtx = "com.google.android.play:core-ktx:1.8.1"
    }

    object Kotlin {
        private const val version = "1.6.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.6.0-native-mt"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object AndroidX {
        object Work {
            private const val version = "2.7.0-alpha04"
            const val runtime = "androidx.work:work-runtime:$version"
            const val ktx = "androidx.work:work-runtime-ktx:$version"
        }

        object Navigation {
            private const val version = "2.3.3"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        const val coreKtx = "androidx.core:core-ktx:1.3.0-rc01"

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Room {
            private const val version = "2.2.5"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }

        object Compose {
            const val version = "1.1.0-alpha02"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIcons = "androidx.compose.material:material-icons-core:$version"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val activity = "androidx.activity:activity-compose:1.3.0-alpha03"

            val all = listOf(
                ui,
                uiTooling,
                foundation,
                material,
                materialIcons,
                materialIconsExtended,
                activity,
            )
        }
    }

    object Dagger {
        private const val version = "2.34.1"
        const val library = "com.google.dagger:dagger:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object Retrofit {
        private const val version = "2.8.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        private const val version = "4.7.2"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Accompanist {
        const val insets = "com.google.accompanist:accompanist-insets:0.15.0"
    }
}
