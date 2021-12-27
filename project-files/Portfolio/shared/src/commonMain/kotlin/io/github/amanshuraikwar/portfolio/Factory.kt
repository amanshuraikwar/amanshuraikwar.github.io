package io.github.amanshuraikwar.portfolio

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json

object Factory {
    fun createHttpClient(
        engine: HttpClientEngine,
        json: Json = createJson(),
        enableNetworkLogs: Boolean
    ) = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }

    fun createHttpClient(
        json: Json = createJson(),
        enableNetworkLogs: Boolean
    ) = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }

    fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }
}