package io.github.amanshuraikwar.portfolio.network.model;

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioDataResponse(
    val name: String,
    val intro: String,
    val links: List<LinkDataResponse>,
    val apps: List<AppDataResponse>,
    val experience: List<ExperienceDataResponse>,
    val madeWith: String,
)