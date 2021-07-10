package com.sample

import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import com.sample.components.*
import com.sample.content.*
import com.sample.style.AppStylesheet
import io.github.amanshuraikwar.portfolio.model.HomePageData
import io.github.amanshuraikwar.portfolio.PortfolioRepository

fun main() {
    val portfolioRepository = PortfolioRepository()

    renderComposable(rootElementId = "root") {
        Style(AppStylesheet)

        Layout {
            for (homePageData in portfolioRepository.getHomePageDataList()) {
                when (homePageData) {
                    is HomePageData.Heading -> {
                        Header(
                            name = homePageData.name,
                            intro = homePageData.intro,
                        )
                    }
                    is HomePageData.MyLinks -> {
                        MyLinks(
                            heading = homePageData.heading,
                            links = homePageData.linkDataList
                        )
                    }
                    is HomePageData.LastUpdated -> {
                        LastUpdated(homePageData.message)
                    }
                    is HomePageData.MadeWith -> {
                        MadeWith(homePageData.message)
                    }
                }
            }
        }
    }
}