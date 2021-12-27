package io.github.amanshuraikwar.portfolio.model

import io.github.amanshuraikwar.portfolio.blog.BlogListDataItem

sealed class PageData {
    data class Home(
        val portfolioData: PortfolioData,
        val blogData: List<BlogListDataItem>,
    ) : PageData()

    data class Md(
        val portfolioData: PortfolioData,
        val mdData: List<MdNode>
    ) : PageData()

    data class Projects(
        val portfolioData: PortfolioData,
    ) : PageData()

    data class Background(
        val portfolioData: PortfolioData,
    ) : PageData()

    data class AboutMe(
        val portfolioData: PortfolioData,
    ) : PageData()
}