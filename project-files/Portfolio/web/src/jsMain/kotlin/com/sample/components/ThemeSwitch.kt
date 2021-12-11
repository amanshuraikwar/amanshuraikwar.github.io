package com.sample.components

import androidx.compose.runtime.Composable

@Composable
fun ThemeSwitch(
    isDarkTheme: Boolean,
    onThemeChange: (isDarkTheme: Boolean) -> Unit,
) {
    ImgButton(
        src = if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/dark_mode_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/light_mode_white_24.svg"
        },
        onClick = {
            onThemeChange(!isDarkTheme)
        }
    )
}