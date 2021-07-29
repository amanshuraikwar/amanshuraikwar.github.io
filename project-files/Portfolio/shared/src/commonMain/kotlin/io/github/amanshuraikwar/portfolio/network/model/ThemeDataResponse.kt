package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ThemeDataResponse(
    val darkTheme: ThemeColorsDataResponse,
    val lightTheme: ThemeColorsDataResponse
)