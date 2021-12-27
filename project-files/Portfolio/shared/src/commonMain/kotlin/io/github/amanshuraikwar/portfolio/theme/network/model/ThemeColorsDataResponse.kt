package io.github.amanshuraikwar.portfolio.theme.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ThemeColorsDataResponse(
    val name: String,
    val isDark: Boolean,
    val primaryColor: String,
    val onPrimaryColor: String,
    val surfaceColor: String,
    val onSurfaceColor: String,
    val errorColor: String,
    val onErrorColor: String,
)