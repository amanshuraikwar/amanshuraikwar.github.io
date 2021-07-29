package io.github.amanshuraikwar.portfolio

import com.russhwolf.settings.Settings
import io.github.amanshuraikwar.portfolio.model.*
import io.github.amanshuraikwar.portfolio.network.PortfolioApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PortfolioRepository {
    private val errorHandler = CoroutineExceptionHandler { _, th ->
        // do nothing
    }
    private val repositoryScope = MainScope() + Dispatchers.Default + errorHandler

    private val portfolioApi = PortfolioApi(
        client = PortfolioApi.createHttpClient(enableNetworkLogs = true)
    )

    private val settings = Settings()
    private var isDarkThemeEnabled =
        settings.getBoolean(PREFS_DARK_THEME_ENABLED, true)

    private val themeData = MutableStateFlow(
        settings
            .getString(
                PREFS_THEME_DATA,
                ""
            )
            .takeIf {
                it.isNotEmpty()
            }
            ?.let {
                Json.decodeFromString<ThemeData>(
                    it
                )
            }
            ?:
            DEFAULT_THEME_DATA
    )

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
                                    "download" -> AppLink.Download(it.url)
                                    else -> AppLink.Other(it.url)
                                }
                            }
                        )
                    },
                    madeWith = response.madeWith
                )
            }
        }
    }

    fun getThemeData(): StateFlow<ThemeData> {
        repositoryScope.launch {
            portfolioApi.getThemeData().let { response ->
                val newThemeData = ThemeData(
                    darkTheme = ThemeColorsData(
                        primaryColor = response.darkTheme.primaryColor,
                        onPrimaryColor = response.darkTheme.onPrimaryColor,
                        surfaceColor = response.darkTheme.surfaceColor,
                        onSurfaceColor = response.darkTheme.onSurfaceColor,
                        errorColor = response.darkTheme.errorColor,
                        onErrorColor = response.darkTheme.onErrorColor,
                    ),
                    lightTheme = ThemeColorsData(
                        primaryColor = response.lightTheme.primaryColor,
                        onPrimaryColor = response.lightTheme.onPrimaryColor,
                        surfaceColor = response.lightTheme.surfaceColor,
                        onSurfaceColor = response.lightTheme.onSurfaceColor,
                        errorColor = response.lightTheme.errorColor,
                        onErrorColor = response.lightTheme.onErrorColor,
                    )
                )
                themeData.value = newThemeData
                settings.putString(PREFS_THEME_DATA, Json.encodeToString(newThemeData))
            }
        }
        return themeData
    }

    companion object {
        private const val PREFS_DARK_THEME_ENABLED = "dark_theme_enabled"
        private const val PREFS_THEME_DATA = "theme_data"

        private val DEFAULT_THEME_DATA = ThemeData(
            darkTheme = ThemeColorsData(
                primaryColor = "#FFFFCDD2",
                onPrimaryColor = "#FF4E342E",
                surfaceColor = "#FF212121",
                onSurfaceColor = "#FFffffff",
                errorColor = "#FFE57373",
                onErrorColor = "#FF4E342E",
            ),
            lightTheme = ThemeColorsData(
                primaryColor = "#ffBD4B4B",
                onPrimaryColor = "#FFFFCDD2",
                surfaceColor = "#ffEEEEEE",
                onSurfaceColor = "#FF212121",
                errorColor = "#FFE57373",
                onErrorColor = "#FF212121",
            )
        )

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