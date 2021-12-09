package com.sample

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.sample.components.Layout
import com.sample.style.AppStylesheet
import io.github.amanshuraikwar.portfolio.PageData
import io.github.amanshuraikwar.portfolio.PortfolioRepository
import io.github.amanshuraikwar.portfolio.model.ThemeData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    val portfolioRepository = PortfolioRepository()
    val coroutineScope = MainScope()

    var pageData: PageData? by mutableStateOf(null)
    var isDarkTheme: Boolean by mutableStateOf(portfolioRepository.isDarkThemeEnabled())
    var themeData: ThemeData by mutableStateOf(portfolioRepository.getThemeData().value)

    coroutineScope.launch {
        pageData = portfolioRepository.getPageData()
    }

    coroutineScope.launch {
        portfolioRepository
            .getThemeData()
            .collect {
                themeData = it
            }
    }

    renderComposable(rootElementId = "root") {
        Style(AppStylesheet)
        LaunchedEffect(key1 = pageData, key2 = isDarkTheme) {
            AppStylesheet.updateColors(
                if (isDarkTheme) {
                    themeData.darkTheme
                } else {
                    themeData.lightTheme
                }
            )
        }

        Layout {
            when (pageData) {
                is PageData.Home -> {
                    HomeView(
                        (pageData as PageData.Home).portfolioData,
                        isDarkTheme = isDarkTheme,
                        onThemeBtnClick = {
                            portfolioRepository.setDarkThemeEnabled(it)
                            isDarkTheme = it
                        },
                    )
                }
                is PageData.Md -> MdView(
                    (pageData as PageData.Md).portfolioData,
                    (pageData as PageData.Md).mdData,
                    isDarkTheme = isDarkTheme,
                    onThemeBtnClick = {
                        portfolioRepository.setDarkThemeEnabled(it)
                        isDarkTheme = it
                    },
                )
                is PageData.Projects -> ProjectsView(
                    (pageData as PageData.Projects).portfolioData,
                    isDarkTheme = isDarkTheme,
                    onThemeBtnClick = {
                        portfolioRepository.setDarkThemeEnabled(it)
                        isDarkTheme = it
                    },
                )
                is PageData.Background -> BackgroundView(
                    (pageData as PageData.Background).portfolioData,
                    isDarkTheme = isDarkTheme,
                    onThemeBtnClick = {
                        portfolioRepository.setDarkThemeEnabled(it)
                        isDarkTheme = it
                    },
                )
                is PageData.AboutMe -> AboutMeView(
                    (pageData as PageData.AboutMe).portfolioData,
                    isDarkTheme = isDarkTheme,
                    onThemeBtnClick = {
                        portfolioRepository.setDarkThemeEnabled(it)
                        isDarkTheme = it
                    },
                )
                null -> {

                }
            }
        }
    }
}