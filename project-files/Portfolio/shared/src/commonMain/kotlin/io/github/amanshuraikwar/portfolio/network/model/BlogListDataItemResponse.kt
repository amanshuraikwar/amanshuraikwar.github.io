package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BlogListDataItemResponse(
    val title: String,
    val date: String,
    val firstParagraph: String,
    val link: String,
    val id: String
)