package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogPageDataResponse(
    @SerialName("blog_data") val blogData: List<Map<String, String>>,
)

