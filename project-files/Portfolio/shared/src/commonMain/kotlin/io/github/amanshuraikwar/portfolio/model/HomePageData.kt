package io.github.amanshuraikwar.portfolio.model

sealed class HomePageData {
    data class Heading(
        val name: String,
        val intro: String
    ) : HomePageData()

    data class MyLinks(
        val heading: String,
        val linkDataList: List<LinkData>
    ) : HomePageData()

    data class LastUpdated(
        val message: String
    ) : HomePageData()

    data class MadeWith(
        val message: String
    ) : HomePageData()
}