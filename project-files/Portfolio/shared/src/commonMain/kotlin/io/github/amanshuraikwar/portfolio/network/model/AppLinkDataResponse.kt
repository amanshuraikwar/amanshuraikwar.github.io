package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AppLinkDataResponse(
    val type: String,
    val url: String,
)