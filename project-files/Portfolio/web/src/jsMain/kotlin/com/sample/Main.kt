package com.sample

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.sample.components.Layout
import com.sample.style.AppStylesheet
import io.github.amanshuraikwar.portfolio.PortfolioRepository
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.model.ThemeData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

sealed class NavigationDestination {
    object Fetching : NavigationDestination()

    data class Home(
        val portfolioData: PortfolioData,
    ) : NavigationDestination()

    data class Md(
        val portfolioData: PortfolioData,
        val mdData: List<MdNode>,
    ) : NavigationDestination()
}

fun main() {
    val portfolioRepository = PortfolioRepository()
    val coroutineScope = MainScope()

    var navigationDestination: NavigationDestination by mutableStateOf(NavigationDestination.Fetching)
    var isDarkTheme: Boolean by mutableStateOf(portfolioRepository.isDarkThemeEnabled())
    var themeData: ThemeData by mutableStateOf(portfolioRepository.getThemeData().value)

    coroutineScope.launch {
        navigationDestination = if (portfolioRepository.isMdEnabled()) {
            NavigationDestination.Md(
                portfolioRepository.getPortfolioData(),
                portfolioRepository.getMdData(),
            )
        } else {
            NavigationDestination.Home(
                portfolioRepository.getPortfolioData(),
            )
        }
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
        LaunchedEffect(key1 = navigationDestination, key2 = isDarkTheme) {
            when (navigationDestination) {
                is NavigationDestination.Home -> {
                    AppStylesheet.updateColors(
                        if (isDarkTheme) {
                            themeData.darkTheme
                        } else {
                            themeData.lightTheme
                        }
                    )
                }
                NavigationDestination.Fetching -> {
                    // do nothing
                }
                is NavigationDestination.Md -> {
                    AppStylesheet.updateColors(
                        if (isDarkTheme) {
                            themeData.darkTheme
                        } else {
                            themeData.lightTheme
                        }
                    )
                }
            }
        }

        Layout {
            when (navigationDestination) {
                is NavigationDestination.Home -> {
                    Home(
                        (navigationDestination as NavigationDestination.Home).portfolioData,
                        isDarkTheme = isDarkTheme,
                        onThemeBtnClick = {
                            portfolioRepository.setDarkThemeEnabled(it)
                            isDarkTheme = it
                        },
                    )
                }
                NavigationDestination.Fetching -> {
                    // do nothing
                }
                is NavigationDestination.Md -> {
                    Md(
                        porfolioData =
                        (navigationDestination as NavigationDestination.Md).portfolioData,
                        mdData = (navigationDestination as NavigationDestination.Md).mdData,
                        isDarkTheme = isDarkTheme,
                        onThemeBtnClick = {
                            portfolioRepository.setDarkThemeEnabled(it)
                            isDarkTheme = it
                        },
                    )
                }
            }
        }
    }
}