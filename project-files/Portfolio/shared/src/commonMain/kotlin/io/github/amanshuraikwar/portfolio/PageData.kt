package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.markdown.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData

sealed class PageData {
    data class Home(val portfolioData: PortfolioData) : PageData()

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