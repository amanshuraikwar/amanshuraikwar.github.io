package io.github.amanshuraikwar.portfolio.network

import io.github.amanshuraikwar.portfolio.network.model.AppDataResponse
import io.github.amanshuraikwar.portfolio.network.model.BlogListDataResponse
import io.github.amanshuraikwar.portfolio.network.model.BlogPageDataResponse
import io.github.amanshuraikwar.portfolio.network.model.PortfolioDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PortfolioApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://amanshuraikwar.github.io/api",
) {
    suspend fun getPortfolioData() =
        client.get("$baseUrl/portfolio_data.json").body<PortfolioDataResponse>()

    suspend fun getAppData(appId: String) =
        client.get("$baseUrl/app_data_$appId.json").body<AppDataResponse>()

    suspend fun getBlogListData() =
        client.get("$baseUrl/blog_list.json").body<BlogListDataResponse>()

    suspend fun getBlogPageData(pageId: String) =
        client.get("$baseUrl/$pageId.json").body<BlogPageDataResponse>()
}