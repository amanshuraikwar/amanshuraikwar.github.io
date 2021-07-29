package io.github.amanshuraikwar.portfolio.network

import io.github.amanshuraikwar.portfolio.network.model.PortfolioDataResponse
import io.github.amanshuraikwar.portfolio.network.model.ThemeDataResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class PortfolioApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://amanshuraikwar.github.io/api",
) {
    suspend fun getPortfolioData() =
        client.get<PortfolioDataResponse>("$baseUrl/portfolio_data.json")

    suspend fun getAppData(appId: String,) =
        client.get<PortfolioDataResponse>("$baseUrl/app_data_$appId.json")

    suspend fun getThemeData() =
        client.get<ThemeDataResponse>("$baseUrl/theme.json")

    companion object {
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