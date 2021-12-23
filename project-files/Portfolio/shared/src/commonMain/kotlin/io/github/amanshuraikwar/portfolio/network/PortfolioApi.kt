package io.github.amanshuraikwar.portfolio.network

import io.github.amanshuraikwar.portfolio.network.model.PortfolioDataResponse
import io.github.amanshuraikwar.portfolio.network.model.ThemeDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.utils.io.core.withBuffer
import kotlinx.serialization.json.Json

class PortfolioApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://amanshuraikwar.github.io/api",
) {
    suspend fun getPortfolioData() =
        client.get<PortfolioDataResponse>("$baseUrl/portfolio_data.json")

    suspend fun getAppData(appId: String) =
        client.get<PortfolioDataResponse>("$baseUrl/app_data_$appId.json")

    suspend fun getThemeData() =
        client.get<ThemeDataResponse>("$baseUrl/theme.json")

    companion object {
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
}