package io.github.amanshuraikwar.portfolio.model

sealed class AppLink {
    data class Github(val repoUrl: String) : AppLink()
    data class PlayStore(val playStoreListingUrl: String) : AppLink()
    data class Download(val downloadUrl: String) : AppLink()
    data class Other(val url: String) : AppLink()
}