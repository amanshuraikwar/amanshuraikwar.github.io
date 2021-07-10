package io.github.amanshuraikwar.portfolio.android.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import io.github.amanshuraikwar.portfolio.android.ui.PortfolioApp
import io.github.amanshuraikwar.portfolio.android.util.setupSystemBars
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val vm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSystemBars(
            isDarkTheme = vm.isDarkTheme.value
        )

        setContent {
            val isDarkTheme by vm.isDarkTheme.collectAsState()

            LaunchedEffect(key1 = isDarkTheme) {
                launch {
                    setupSystemBars(
                        isDarkTheme = isDarkTheme
                    )
                }
            }

            PortfolioApp(isDark = isDarkTheme) {
                val coroutineScope = rememberCoroutineScope()
                val screenState by vm.screenState.collectAsState()
                HomeScreen(
                    screenState = screenState,
                    onLinkClick = { url ->
                        coroutineScope.launch { goToUrl(url) }
                    }
                )
            }
        }
    }

    private fun goToUrl(url: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }
}
