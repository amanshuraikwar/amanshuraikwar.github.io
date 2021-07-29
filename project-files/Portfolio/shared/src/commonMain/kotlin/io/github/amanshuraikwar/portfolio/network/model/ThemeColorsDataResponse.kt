package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ThemeColorsDataResponse(
    val primaryColor: String,
    val onPrimaryColor: String,
    val surfaceColor: String,
    val onSurfaceColor: String,
    val errorColor: String,
    val onErrorColor: String,
)