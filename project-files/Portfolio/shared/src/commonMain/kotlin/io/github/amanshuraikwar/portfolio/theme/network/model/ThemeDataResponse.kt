package io.github.amanshuraikwar.portfolio.theme.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ThemeDataResponse(
    val themes: List<ThemeColorsDataResponse>,
)