package io.github.amanshuraikwar.portfolio

import com.russhwolf.settings.Settings
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import io.github.amanshuraikwar.portfolio.model.AppData
import io.github.amanshuraikwar.portfolio.model.AppLink
import io.github.amanshuraikwar.portfolio.model.ExperienceData
import io.github.amanshuraikwar.portfolio.model.LinkData
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.model.ThemeColorsData
import io.github.amanshuraikwar.portfolio.model.ThemeData
import io.github.amanshuraikwar.portfolio.network.PortfolioApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
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
            ?: DEFAULT_THEME_DATA
    )

    fun isDarkThemeEnabled(): Boolean {
        return isDarkThemeEnabled
    }

    fun setDarkThemeEnabled(enabled: Boolean) {
        isDarkThemeEnabled = enabled
        settings.putBoolean(PREFS_DARK_THEME_ENABLED, enabled)
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
                            appDataResponse.maintained,
                            appDataResponse.art,
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
                    experience = response.experience.map {
                        ExperienceData(
                            title = it.title,
                            location = it.location,
                            dateRange = it.dateRange,
                            content = it.content
                        )
                    },
                    madeWith = response.madeWith
                )
            }
        }
    }

    // to be called from Kotlin/Native client
    fun getPortfolioData(callback: (PortfolioData) -> Unit) {
        repositoryScope.launch {
            callback(getPortfolioData())
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

    suspend fun getPageData(): PageData {
        return when (GeneratedDataStore().getPageType()) {
            PageType.HOME -> PageData.Home(getPortfolioData(), BlogListDataStore().getBlogListData())
            PageType.MD -> PageData.Md(getPortfolioData(), GeneratedDataStore().getData())
            PageType.PROJECTS -> PageData.Projects(getPortfolioData())
            PageType.BACKGROUND -> PageData.Background(getPortfolioData())
            PageType.ABOUT_ME -> PageData.AboutMe(getPortfolioData())
        }
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
    }
}