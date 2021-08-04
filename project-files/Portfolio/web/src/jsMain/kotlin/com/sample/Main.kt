package com.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import com.sample.components.Layout
import com.sample.style.AppStylesheet
import com.sample.style.ColorPalette
import io.github.amanshuraikwar.portfolio.PortfolioRepository
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.model.ThemeData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

sealed class NavigationDestination {
    object Fetching : NavigationDestination()
    data class Home(val portfolioData: PortfolioData) : NavigationDestination()
    object NextBus : NavigationDestination()
}

val colorPaletteList = listOf(
    // dark
    ColorPalette(
        colorBackground = Color("#212121"),
        colorOnBackground = Color("#ffffff"),
        colorPrimary = Color("#FFCDD2"),
        colorOnPrimary = Color("#4E342E"),
    ),
    // light
    ColorPalette(
        colorBackground = Color("#FFF2CA"),
        colorOnBackground = Color("#52403A"),
        colorPrimary = Color("#F57C00"),
        colorOnPrimary = Color("#4E342E"),
    )
)

val nextBusColorPalette = ColorPalette(
    colorBackground = Color("#212121"),
    colorOnBackground = Color("#eef0f2"),
    colorPrimary = Color("#64B5F6"),
    colorOnPrimary = Color("#212121"),
)

fun main() {
    val portfolioRepository = PortfolioRepository()
    val coroutineScope = MainScope()

    var navigationDestination: NavigationDestination by mutableStateOf(NavigationDestination.Fetching)
    var isDarkTheme: Boolean by mutableStateOf(portfolioRepository.isDarkThemeEnabled())
    var themeData: ThemeData by mutableStateOf(portfolioRepository.getThemeData().value)

    coroutineScope.launch {
        navigationDestination = NavigationDestination.Home(portfolioRepository.getPortfolioData())
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
                        onNextBusClick = {
                            navigationDestination = NavigationDestination.NextBus
                        },
                        isDarkTheme = isDarkTheme,
                        onThemeBtnClick = {
                            portfolioRepository.setDarkThemeEnabled(it)
                            isDarkTheme = it
                        }
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