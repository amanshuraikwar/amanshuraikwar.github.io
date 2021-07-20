package com.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import com.sample.components.Layout
import com.sample.style.AppStylesheet
import com.sample.style.ColorPalette
import io.github.amanshuraikwar.portfolio.PortfolioRepository
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

sealed class NavigationDestination {
    object Home : NavigationDestination()
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

    var navigationDestination: NavigationDestination by mutableStateOf(NavigationDestination.Home)
    var isDarkTheme: Boolean by mutableStateOf(portfolioRepository.isDarkThemeEnabled())

    renderComposable(rootElementId = "root") {
        Style(AppStylesheet)
        LaunchedEffect(key1 = navigationDestination, key2 = isDarkTheme) {
            when (navigationDestination) {
                NavigationDestination.Home -> {
                    AppStylesheet.updateColors(
                        colorPalette = if (isDarkTheme) {
                            colorPaletteList[0]
                        } else {
                            colorPaletteList[1]
                        }
                    )
                }
                NavigationDestination.NextBus -> {
                    AppStylesheet.updateColors(colorPalette = nextBusColorPalette)
                }
            }
        }

        Layout {
            when (navigationDestination) {
                NavigationDestination.Home -> {
                    Home(
                        portfolioRepository.getHomePageDataList(),
                        onNextBusClick = {
                            navigationDestination = NavigationDestination.NextBus
                        },
                        isDarkTheme = isDarkTheme,
                        onThemeBtnClick = {
                            portfolioRepository.setDarkThemeEnabled(!isDarkTheme)
                            isDarkTheme = !isDarkTheme
                        }
                    )
                }
                NavigationDestination.NextBus -> {
                    NextBus {
                        navigationDestination = NavigationDestination.Home
                    }
                }
            }
        }
    }
}