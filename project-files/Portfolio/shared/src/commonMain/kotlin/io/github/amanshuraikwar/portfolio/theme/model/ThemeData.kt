package io.github.amanshuraikwar.portfolio.theme.model

import kotlinx.serialization.Serializable

@Serializable
data class ThemeData(
    val themes: List<ThemeColorsData>
)