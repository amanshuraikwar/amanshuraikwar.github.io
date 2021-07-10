package io.github.amanshuraikwar.portfolio.android.util

import android.app.Activity
import android.graphics.Color
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.doOnLayout

fun Activity.setupSystemBars(
    isDarkTheme: Boolean
) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.apply {
        statusBarColor = Color.TRANSPARENT
        navigationBarColor = Color.TRANSPARENT
    }
    window.decorView.doOnLayout {
        WindowInsetsControllerCompat(window, it).apply {
            isAppearanceLightNavigationBars = !isDarkTheme
            isAppearanceLightStatusBars = !isDarkTheme
        }
    }
}