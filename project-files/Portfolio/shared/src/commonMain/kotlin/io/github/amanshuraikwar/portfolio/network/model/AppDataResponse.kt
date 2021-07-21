package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AppDataResponse(
    val id: String,
    val title: String,
)