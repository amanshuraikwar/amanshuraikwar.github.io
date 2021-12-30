package io.github.amanshuraikwar.portfolio.blog

data class BlogListDataItem(
    val title: String,
    val date: String,
    val firstParagraph: String,
    val link: String,
    val id: String = link.split("/").last()
)