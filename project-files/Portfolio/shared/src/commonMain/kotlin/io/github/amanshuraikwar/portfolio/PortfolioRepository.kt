package io.github.amanshuraikwar.portfolio

import com.russhwolf.settings.Settings
import io.github.amanshuraikwar.portfolio.model.*
import io.github.amanshuraikwar.portfolio.network.PortfolioApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioRepository {
    private val portfolioApi = PortfolioApi(
        client = PortfolioApi.createHttpClient(enableNetworkLogs = true)
    )

    private val settings = Settings()
    private var isDarkThemeEnabled =
        settings.getBoolean(PREFS_DARK_THEME_ENABLED, true)

    fun isDarkThemeEnabled(): Boolean {
        return isDarkThemeEnabled
    }

    fun setDarkThemeEnabled(enabled: Boolean) {
        isDarkThemeEnabled = enabled
        settings.putBoolean(PREFS_DARK_THEME_ENABLED, enabled)
    }

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
                message = "Last updated on 20 July 2021 :)"
            ),

            HomePageData.MadeWith(
                message = "Made with Kotlin Multiplatform Mobile & Jetpack Compose"
            )
        )
    }

    suspend fun getPortfolioData(): PortfolioData {
        return withContext(Dispatchers.Default) {
            portfolioApi.getPortfolioData().let { response ->
                PortfolioData(
                    name = response.name,
                    intro = response.intro,
                    links = response.links.map { (id, title, url) ->
                        LinkData(id, title, url)
                    },
                    apps = response.apps.map { appDataResponse ->
                        AppData(
                            appDataResponse.id,
                            appDataResponse.title,
                            appDataResponse.description,
                            appDataResponse.appLinks.map {
                                when (it.type) {
                                    "github" -> AppLink.Github(it.url)
                                    "playstore" -> AppLink.PlayStore(it.url)
                                    else -> AppLink.Other(it.url)
                                }
                            }
                        )
                    }
                )
            }
        }
    }

    companion object {
        private const val PREFS_DARK_THEME_ENABLED = "dark_theme_enabled"

        private val links = listOf<LinkData>(
//            LinkData(
//                title = "RESUME",
//                url = "https://amanshuraikwar.github.io/assets/resume/Resume-7-Aug-2019.pdf",
//            ),
//            LinkData(
//                title = "GITHUB",
//                url = "https://github.com/amanshuraikwar",
//            ),
//            LinkData(
//                title = "LINKEDIN",
//                url = "https://www.linkedin.com/in/amanshu-raikwar-36b534103/",
//            ),
//            LinkData(
//                title = "MEDIUM",
//                url = "https://medium.com/@amanshuraikwar.in/",
//            ),
//            LinkData(
//                title = "PLAY STORE",
//                url = "https://play.google.com/store/apps/developer?id=Amanshu%20Raikwar&hl=en",
//            ),
//            LinkData(
//                title = "INSTAGRAM",
//                url = "https://instagram.com/amanshuraikwar",
//            ),
//            LinkData(
//                title = "UNSPLASH",
//                url = "https://unsplash.com/@amanshuraikwar",
//            ),
//            LinkData(
//                title = "TWITTER",
//                url = "https://twitter.com/amanshuraikwar_",
//            ),
        )
    }
}