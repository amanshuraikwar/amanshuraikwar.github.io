package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LinkDataResponse(
    val id: String,
    val title: String,
    val url: String,
)