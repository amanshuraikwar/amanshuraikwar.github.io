package io.github.amanshuraikwar.portfolio.network

import io.github.amanshuraikwar.portfolio.network.model.PortfolioDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class PortfolioApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://amanshuraikwar.github.io/api",
) {
    suspend fun getPortfolioData() =
        client.get<PortfolioDataResponse>("$baseUrl/portfolio_data.json")

    suspend fun getAppData(appId: String) =
        client.get<PortfolioDataResponse>("$baseUrl/app_data_$appId.json")
}