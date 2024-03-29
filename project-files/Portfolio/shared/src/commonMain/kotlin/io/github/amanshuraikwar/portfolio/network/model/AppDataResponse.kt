package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AppDataResponse(
    val id: String,
    val title: String,
    val description: String,
    val maintained: Boolean,
    val art: String,
    val appLinks: List<AppLinkDataResponse>
)