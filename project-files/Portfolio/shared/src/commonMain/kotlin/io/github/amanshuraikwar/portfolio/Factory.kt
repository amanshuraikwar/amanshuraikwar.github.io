package io.github.amanshuraikwar.portfolio

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object Factory {
    fun createHttpClient(
        engine: HttpClientEngine,
        json: Json = createJson(),
        enableNetworkLogs: Boolean
    ) = HttpClient(engine) {
        install(ContentNegotiation) {
            json(json)
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
        install(ContentNegotiation) {
            json(json)
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