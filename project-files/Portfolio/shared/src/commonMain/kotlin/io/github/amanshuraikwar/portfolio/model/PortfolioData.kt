package io.github.amanshuraikwar.portfolio.model

data class PortfolioData(
    val name: String,
    val intro: String,
    val links: List<LinkData>,
    val projects: List<ProjectData>,
    val background: List<BackgroundData>,
    val madeWith: String,
)