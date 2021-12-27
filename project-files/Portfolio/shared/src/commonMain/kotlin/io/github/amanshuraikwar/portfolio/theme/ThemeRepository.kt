@file:Suppress("EXPERIMENTAL_API_USAGE")

package io.github.amanshuraikwar.portfolio.theme

import com.russhwolf.settings.Settings
import io.github.amanshuraikwar.portfolio.Factory
import io.github.amanshuraikwar.portfolio.theme.model.ThemeColorsData
import io.github.amanshuraikwar.portfolio.theme.model.ThemeData
import io.github.amanshuraikwar.portfolio.theme.network.ThemeApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ThemeRepository(
    private val themeApi: ThemeApi = ThemeApi(
        Factory.createHttpClient(enableNetworkLogs = true)
    ),
    private val settings: Settings = Settings(),
    private val defaultThemeColorsName: String = DEFAULT_THEME_COLORS_NAME,
    private val defaultThemeData: ThemeData = DEFAULT_THEME_DATA,
) {
    private val errorHandler = CoroutineExceptionHandler { _, th ->
        // do nothing
    }
    private val repositoryScope = MainScope() + Dispatchers.Default + errorHandler

    private var themeColorsName: String
        get() {
            return settings.getString(
                PREFS_THEME_COLORS_NAME,
                defaultThemeColorsName.replace(" ", "").lowercase()
            )
        }
        set(value) {
            settings.putString(
                PREFS_THEME_COLORS_NAME,
                value
            )
        }

    private val themeColorsNameFlow: MutableStateFlow<String>

    private var themeData: ThemeData
        get() {
            return try {
                Json.decodeFromString(
                    settings.getString(
                        PREFS_THEME_DATA,
                        Json.encodeToString(defaultThemeData)
                    )
                )
            } catch (e: Exception) {
                settings.putString(
                    PREFS_THEME_DATA,
                    Json.encodeToString(defaultThemeData)
                )
                DEFAULT_THEME_DATA
            }
        }
        set(value) {
            settings.putString(
                PREFS_THEME_DATA,
                Json.encodeToString(value)
            )
        }

    private val themeDataFlow = MutableStateFlow(themeData)

    private val themeColors: MutableStateFlow<ThemeColorsData>

    init {
        val initThemeColors: ThemeColorsData

        while (true) {
            val selectedThemeColors = themeData.getThemeColors(themeColorsName)
            if (selectedThemeColors != null) {
                initThemeColors = selectedThemeColors
                break
            }
            val parsedName = themeData.themes[0]
                .name
                .replace(" ", "")
                .lowercase()
            themeColorsName = parsedName
        }

        themeColorsNameFlow = MutableStateFlow(themeColorsName)
        themeColors = MutableStateFlow(initThemeColors)
    }

    fun getSelectedThemeColorsName(): StateFlow<String> {
        return themeColorsNameFlow
    }

    fun setSelectedThemeColorsName(name: String) {
        val parsedName = name.replace(" ", "").lowercase()
        val selectedThemeColors = themeData.getThemeColors(parsedName)
        if (selectedThemeColors != null) {
            themeColorsName = parsedName
            themeColorsNameFlow.value = themeColorsName
            themeColors.value = selectedThemeColors
        }
    }

    fun getSelectedThemeColors(): StateFlow<ThemeColorsData> {
        return themeColors
    }

    fun getThemeData(): StateFlow<ThemeData> {
        repositoryScope.launch {
            fetchThemeDataFromRemote()
        }

        return themeDataFlow
    }

    private suspend fun fetchThemeDataFromRemote() {
        themeApi.getThemeData()
            .takeIf {
                it.themes.isNotEmpty()
            }
            ?.let { response ->
                val newThemeData = ThemeData(
                    response.themes.map { themeColors ->
                        ThemeColorsData(
                            name = themeColors.name,
                            isDark = themeColors.isDark,
                            primaryColor = themeColors.primaryColor,
                            onPrimaryColor = themeColors.onPrimaryColor,
                            surfaceColor = themeColors.surfaceColor,
                            onSurfaceColor = themeColors.onSurfaceColor,
                            errorColor = themeColors.errorColor,
                            onErrorColor = themeColors.onErrorColor,
                        )
                    }
                )

                themeData = newThemeData
                themeDataFlow.value = themeData

                while (true) {
                    val newThemeColors = themeData.getThemeColors(themeColorsName)
                    if (newThemeColors != null) {
                        themeColorsNameFlow.value = themeColorsName
                        themeColors.value = newThemeColors
                        break
                    }
                    val parsedName = themeData.themes[0]
                        .name
                        .replace(" ", "")
                        .lowercase()
                    themeColorsName = parsedName
                }
            }
    }

    companion object {
        private fun ThemeData.getThemeColors(name: String): ThemeColorsData? {
            return themes.find {
                it.name.replace(" ", "").lowercase() == name
            }
        }

        private const val PREFS_THEME_COLORS_NAME = "theme_colors_name"
        private const val PREFS_THEME_DATA = "theme_data"

        private val DEFAULT_THEME_DATA = ThemeData(
            listOf(
                ThemeColorsData(
                    name = "Dark Salmon",
                    isDark = true,
                    primaryColor = "#FFFFCDD2",
                    onPrimaryColor = "#FF4E342E",
                    surfaceColor = "#FF212121",
                    onSurfaceColor = "#FFffffff",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF4E342E",
                ),
                ThemeColorsData(
                    name = "Light Blue",
                    isDark = false,
                    primaryColor = "#ffEA5C5A",
                    onPrimaryColor = "#FFffffff",
                    surfaceColor = "#ffCDECF9",
                    onSurfaceColor = "#FF030204",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF212121",
                ),
                ThemeColorsData(
                    name = "Matt D'av Ella",
                    isDark = false,
                    primaryColor = "#ffE35638",
                    onPrimaryColor = "#FFFADACA",
                    surfaceColor = "#ffFBF8EC",
                    onSurfaceColor = "#FF24242C",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF212121",
                )
            )
        )

        private val DEFAULT_THEME_COLORS_NAME =
            DEFAULT_THEME_DATA.themes[0].name.replace(" ", "").lowercase()
    }
}