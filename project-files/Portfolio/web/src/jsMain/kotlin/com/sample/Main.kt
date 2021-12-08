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
        val blogData: List<MdNode>,
    ) : NavigationDestination()

    object NextBus : NavigationDestination()
}

fun main() {
    val portfolioRepository = PortfolioRepository()
    val coroutineScope = MainScope()

    var navigationDestination: NavigationDestination by mutableStateOf(NavigationDestination.Fetching)
    var isDarkTheme: Boolean by mutableStateOf(portfolioRepository.isDarkThemeEnabled())
    var themeData: ThemeData by mutableStateOf(portfolioRepository.getThemeData().value)

    coroutineScope.launch {
        navigationDestination = NavigationDestination.Home(
            portfolioRepository.getPortfolioData(),
            portfolioRepository.getBlogData()
        )
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
                NavigationDestination.NextBus -> {
                    //AppStylesheet.updateColors(colorPalette = nextBusColorPalette)
                }
            }
        }

        Layout {
            when (navigationDestination) {
                is NavigationDestination.Home -> {
                    Home(
                        (navigationDestination as NavigationDestination.Home).portfolioData,
                        (navigationDestination as NavigationDestination.Home).blogData,
                        onNextBusClick = {
                            navigationDestination = NavigationDestination.NextBus
                        },
                        isDarkTheme = isDarkTheme,
                        onThemeBtnClick = {
                            portfolioRepository.setDarkThemeEnabled(it)
                            isDarkTheme = it
                        },
                    )
                }
                NavigationDestination.NextBus -> {
                    NextBus {
                        //navigationDestination = NavigationDestination.Home
                    }
                }
            }
        }
    }
}