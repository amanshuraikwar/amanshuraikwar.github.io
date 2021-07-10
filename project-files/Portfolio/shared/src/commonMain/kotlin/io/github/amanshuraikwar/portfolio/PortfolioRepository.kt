package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.model.HomePageData
import io.github.amanshuraikwar.portfolio.model.LinkData

class PortfolioRepository {
    private val links = listOf(
        LinkData(
            name = "RESUME",
            url = "https://amanshuraikwar.github.io/assets/resume/Resume-7-Aug-2019.pdf",
        ),
        LinkData(
            name = "GITHUB",
            url = "https://github.com/amanshuraikwar",
        ),
        LinkData(
            name = "LINKEDIN",
            url = "https://www.linkedin.com/in/amanshu-raikwar-36b534103/",
        ),
        LinkData(
            name = "MEDIUM",
            url = "https://medium.com/@amanshuraikwar.in/",
        ),
        LinkData(
            name = "PLAY STORE",
            url = "https://play.google.com/store/apps/developer?id=Amanshu%20Raikwar&hl=en",
        ),
        LinkData(
            name = "INSTAGRAM",
            url = "https://instagram.com/amanshuraikwar",
        ),
        LinkData(
            name = "UNSPLASH",
            url = "https://unsplash.com/@amanshuraikwar",
        ),
        LinkData(
            name = "TWITTER",
            url = "https://twitter.com/amanshuraikwar_",
        ),
    )

    fun getHomePageDataList(): List<HomePageData> {
        return listOf(
            HomePageData.Heading(
                name = "Amanshu Raikwar",
                intro = "I'm a Software Engineer, building stuff for the lil green droid."
            ),

            HomePageData.MyLinks(
                heading = "You can find me at...",
                linkDataList = links
            ),

            HomePageData.LastUpdated(
                message = "Last updated on 10 July 2021 :)"
            ),

            HomePageData.MadeWith(
                message = "Made with Kotlin Multiplatform Mobile & Jetpack Compose"
            )
        )
    }
}