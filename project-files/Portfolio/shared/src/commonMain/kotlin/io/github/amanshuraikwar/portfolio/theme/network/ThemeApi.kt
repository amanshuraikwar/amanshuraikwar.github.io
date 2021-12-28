package io.github.amanshuraikwar.portfolio.theme.network

import io.github.amanshuraikwar.portfolio.theme.network.model.ThemeDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ThemeApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://amanshuraikwar.github.io/api",
) {
    suspend fun getThemeData()=
        client.get("$baseUrl/theme.json").body<ThemeDataResponse>()
}