package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ExperienceDataResponse(
    val title: String,
    val location: String,
    val dateRange: String,
    val content: List<String>,
)