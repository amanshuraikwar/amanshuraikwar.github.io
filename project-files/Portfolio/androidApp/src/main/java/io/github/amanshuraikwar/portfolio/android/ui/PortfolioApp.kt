package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import io.github.amanshuraikwar.portfolio.android.theme.PortfolioTheme
import io.github.amanshuraikwar.portfolio.android.theme.rippleColor

@Composable
fun PortfolioApp(
    isDark: Boolean,
    content: @Composable () -> Unit,
) {
    PortfolioTheme(isDark) {
        ProvideWindowInsets {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                CompositionLocalProvider(
                    LocalIndication provides rememberRipple(
                        color = MaterialTheme.colors.rippleColor
                    )
                ) {
                    content()
                }
            }
        }
    }
}