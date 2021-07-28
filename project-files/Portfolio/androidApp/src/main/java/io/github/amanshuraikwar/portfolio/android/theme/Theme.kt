package io.github.amanshuraikwar.portfolio.android.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalettePink = darkColors(
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

private val LightColorPaletteOrange = lightColors(
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

private val LightColorPalettePink = lightColors(
    primary = pinkLight,
    primaryVariant = pinkLight,
    secondary = pinkLight,
    secondaryVariant = pinkLight,
    background = white,
    surface = white,
    error = redLight,
    onPrimary = brown,
    onSecondary = brown,
    onBackground = gray900,
    onSurface = gray900,
    onError = gray900
)

private val LightColorPaletteRed = lightColors(
    primary = redMkbhd,
    primaryVariant = redMkbhd,
    secondary = redMkbhd,
    secondaryVariant = redMkbhd,
    background = lightGrey,
    surface = lightGrey,
    error = redLight,
    onPrimary = pinkLight,
    onSecondary = pinkLight,
    onBackground = gray900,
    onSurface = gray900,
    onError = gray900
)

val Colors.rippleColor: Color
    get() = if (isLight) {
        brown
    } else {
        pink
    }

@Composable
fun PortfolioTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalettePink
    } else {
        LightColorPaletteRed
    }

    MaterialTheme(
        colors = colors,
        typography = PortfolioTypography,
        shapes = PortfolioThemeShapes,
        content = content
    )
}
