package io.github.amanshuraikwar.portfolio.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogListDataResponse(
    @SerialName("blog_list") val blogList: List<BlogListDataItemResponse>,
)

