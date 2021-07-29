package io.github.amanshuraikwar.portfolio.model

data class PortfolioData(
    val name: String,
    val intro: String,
    val links: List<LinkData>,
    val apps: List<AppData>,
    val madeWith: String,
)