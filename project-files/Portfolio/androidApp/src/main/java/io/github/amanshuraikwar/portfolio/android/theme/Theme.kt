package io.github.amanshuraikwar.portfolio.android.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Deprecated("use from remote")
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

@Deprecated("use from remote")
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

@Deprecated("use from remote")
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

@Deprecated("use from remote")
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

val Color.secondary: Color
    get() = copy(alpha = 0.8f)

val Color.disabled: Color
    get() = copy(alpha = 0.38f)

fun Color(color: String): Color {
    return Color(value = android.graphics.Color.parseColor(color).toULong() shl 32)
}

@Composable
fun PortfolioTheme(
    isDark: Boolean,
    themeState: ThemeState,
    content: @Composable () -> Unit
) {
    val primary by animateColorAsState(
        targetValue = if (isDark) {
            Color(themeState.darkColors.primaryColor)
        } else {
            Color(themeState.lightColor.primaryColor)
        }
    )

    val onPrimary by animateColorAsState(
        targetValue = if (isDark) {
            Color(themeState.darkColors.onPrimaryColor)
        } else {
            Color(themeState.lightColor.onPrimaryColor)
        }
    )

    val surface by animateColorAsState(
        targetValue = if (isDark) {
            Color(themeState.darkColors.surfaceColor)
        } else {
            Color(themeState.lightColor.surfaceColor)
        }
    )

    val onSurface by animateColorAsState(
        targetValue = if (isDark) {
            Color(themeState.darkColors.onSurfaceColor)
        } else {
            Color(themeState.lightColor.onSurfaceColor)
        }
    )

    val error by animateColorAsState(
        targetValue = if (isDark) {
            Color(themeState.darkColors.errorColor)
        } else {
            Color(themeState.lightColor.errorColor)
        }
    )

    val onError by animateColorAsState(
        targetValue = if (isDark) {
            Color(themeState.darkColors.onErrorColor)
        } else {
            Color(themeState.lightColor.onErrorColor)
        }
    )

    MaterialTheme(
        colors = Colors(
            primary = primary,
            primaryVariant = primary,
            secondary = primary,
            secondaryVariant = primary,
            background = surface,
            surface = surface,
            error = error,
            onPrimary = onPrimary,
            onSecondary = onPrimary,
            onBackground = onSurface,
            onSurface = onSurface,
            onError = onError,
            isLight = !isDark,
        ),
        typography = PortfolioTypography,
        shapes = PortfolioThemeShapes,
        content = content
    )
}
