package com.sample.components

import androidx.compose.runtime.Composable

@Composable
fun ThemeSwitch(
    isDarkTheme: Boolean,
    onThemeChange: (isDarkTheme: Boolean) -> Unit,
) {
    ImgButton(
        src = if (isDarkTheme) {
            "assets/dark_mode_black_24.svg"
        } else {
            "assets/light_mode_white_24.svg"
        },
        onClick = {
            onThemeChange(!isDarkTheme)
        }
    )
}