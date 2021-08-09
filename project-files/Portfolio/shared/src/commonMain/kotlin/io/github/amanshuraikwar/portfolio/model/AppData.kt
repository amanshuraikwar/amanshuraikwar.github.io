package io.github.amanshuraikwar.portfolio.model

data class AppData(
    val id: String,
    val title: String,
    val description: String,
    val maintained: Boolean,
    val artUrl: String,
    val appLinks: List<AppLink>
)