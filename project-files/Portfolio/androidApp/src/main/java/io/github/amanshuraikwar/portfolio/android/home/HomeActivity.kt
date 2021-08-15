package io.github.amanshuraikwar.portfolio.android.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import io.github.amanshuraikwar.portfolio.android.ui.FillFirstColumn
import io.github.amanshuraikwar.portfolio.android.ui.PortfolioApp
import io.github.amanshuraikwar.portfolio.android.util.setupSystemBars
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val vm: HomeViewModel by viewModels()

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSystemBars(
            isDarkTheme = vm.isDarkTheme.value
        )

        setContent {
            val isDarkTheme by vm.isDarkTheme.collectAsState()
            val themeState by vm.themeState.collectAsState()

            LaunchedEffect(key1 = isDarkTheme) {
                launch {
                    setupSystemBars(
                        isDarkTheme = isDarkTheme
                    )
                }
            }

            PortfolioApp(
                isDark = isDarkTheme,
                themeState = themeState,
            ) {
                val coroutineScope = rememberCoroutineScope()
                val screenState by vm.screenState.collectAsState()

                FillFirstColumn {
                    HomeScreen(
                        screenState = screenState,
                        onLinkClick = { url ->
                            coroutineScope.launch { goToUrl(url) }
                        },
                        isDarkTheme = isDarkTheme,
                        onThemeSwitchValueChange = { newValue ->
                            vm.setDarkThemeEnabled(newValue)
                        },
                    )
                }
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
