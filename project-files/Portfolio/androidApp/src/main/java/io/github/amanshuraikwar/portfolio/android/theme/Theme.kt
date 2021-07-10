package io.github.amanshuraikwar.portfolio.android.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = pinkLight,
    primaryVariant = pinkLight,
    secondary = pinkLight,
    secondaryVariant = pinkLight,
    background = gray900,
    surface = gray900,
    error = redLight,
    onPrimary = brownDark,
    onSecondary = brownDark,
    onBackground = white,
    onSurface = white,
    onError = brownDark
)

private val LightColorPalette = lightColors(
    primary = orange,
    primaryVariant = orange,
    secondary = orange,
    secondaryVariant = orange,
    background = orangeLight,
    surface = orangeLight,
    error = red,
    onPrimary = orangeLight,
    onSecondary = orangeLight,
    onBackground = brown,
    onSurface = brown,
    onError = orangeLight
)

val Colors.rippleColor: Color
    get() = if (isLight) {
        orange
    } else {
        pink
    }

@Composable
fun PortfolioTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = PortfolioTypography,
        shapes = PortfolioThemeShapes,
        content = content
    )
}
