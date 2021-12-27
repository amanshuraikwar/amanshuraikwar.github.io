package com.sample

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.sample.components.Layout
import com.sample.style.AppStylesheet
import io.github.amanshuraikwar.portfolio.WebPageBuildPortfolioRepository
import io.github.amanshuraikwar.portfolio.model.PageData
import io.github.amanshuraikwar.portfolio.theme.ThemeRepository
import io.github.amanshuraikwar.portfolio.theme.model.ThemeColorsData
import io.github.amanshuraikwar.portfolio.theme.model.ThemeData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    val portfolioRepository = WebPageBuildPortfolioRepository()
    val themeRepository = ThemeRepository()
    val coroutineScope = MainScope()
    var pageData: PageData? by mutableStateOf(null)

    coroutineScope.launch {
        pageData = portfolioRepository.getPageData()
    }

    renderComposable(rootElementId = "root") {
        val themeColors: ThemeColorsData by themeRepository
            .getSelectedThemeColors()
            .collectAsState()

        val themeColorsName: String by themeRepository
            .getSelectedThemeColorsName()
            .collectAsState()

        val themeData: ThemeData by themeRepository
            .getThemeData()
            .collectAsState()

        Style(AppStylesheet)

        LaunchedEffect(key1 = pageData, key2 = themeColors) {
            AppStylesheet.updateColors(themeColors)
        }

        Layout {
            when (pageData) {
                is PageData.Home -> {
                    HomeView(
                        (pageData as PageData.Home).portfolioData,
                        (pageData as PageData.Home).blogData,
                        themeColorsName = themeColorsName,
                        isDarkTheme = themeColors.isDark,
                        themeData = themeData,
                        onThemeBtnClick = {
                            themeRepository.setSelectedThemeColorsName(it)
                        },
                    )
                }
                is PageData.Md -> MdView(
                    (pageData as PageData.Md).portfolioData,
                    (pageData as PageData.Md).mdData,
                    themeColorsName = themeColorsName,
                    isDarkTheme = themeColors.isDark,
                    themeData = themeData,
                    onThemeBtnClick = {
                        themeRepository.setSelectedThemeColorsName(it)
                    },
                )
                is PageData.Projects -> ProjectsView(
                    (pageData as PageData.Projects).portfolioData,
                    themeColorsName = themeColorsName,
                    isDarkTheme = themeColors.isDark,
                    themeData = themeData,
                    onThemeBtnClick = {
                        themeRepository.setSelectedThemeColorsName(it)
                    },
                )
                is PageData.Background -> BackgroundView(
                    (pageData as PageData.Background).portfolioData,
                    themeColorsName = themeColorsName,
                    isDarkTheme = themeColors.isDark,
                    themeData = themeData,
                    onThemeBtnClick = {
                        themeRepository.setSelectedThemeColorsName(it)
                    },
                )
                is PageData.AboutMe -> AboutMeView(
                    (pageData as PageData.AboutMe).portfolioData,
                    themeColorsName = themeColorsName,
                    isDarkTheme = themeColors.isDark,
                    themeData = themeData,
                    onThemeBtnClick = {
                        themeRepository.setSelectedThemeColorsName(it)
                    },
                )
                null -> {

                }
            }
        }
    }
}