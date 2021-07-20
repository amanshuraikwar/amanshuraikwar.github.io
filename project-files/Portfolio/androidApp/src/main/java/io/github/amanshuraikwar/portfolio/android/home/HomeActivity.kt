package io.github.amanshuraikwar.portfolio.android.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.DarkMode
import androidx.compose.material.icons.twotone.LightMode
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import io.github.amanshuraikwar.portfolio.android.ui.FillFirstColumn
import io.github.amanshuraikwar.portfolio.android.ui.PortfolioApp
import io.github.amanshuraikwar.portfolio.android.ui.ThemeSwitch
import io.github.amanshuraikwar.portfolio.android.ui.ThemeSwitchValue
import io.github.amanshuraikwar.portfolio.android.util.setupSystemBars
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val vm: HomeViewModel by viewModels()

    @ExperimentalMaterialApi
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

                FillFirstColumn {
                    HomeScreen(
                        screenState = screenState,
                        onLinkClick = { url ->
                            coroutineScope.launch { goToUrl(url) }
                        }
                    )

                    Surface(
                        elevation = 16.dp,
                        color = MaterialTheme.colors.surface
                    ) {
                        Box(
                            Modifier
                                .navigationBarsPadding()
                                .padding(16.dp),
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = "App Theme",
                                style = MaterialTheme.typography.h6
                            )

                            ThemeSwitch(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                value = if (isDarkTheme) {
                                    ThemeSwitchValue.DARK
                                } else {
                                    ThemeSwitchValue.LIGHT
                                },
                                lightThemeIcon = Icons.TwoTone.LightMode,
                                darkThemeIcon = Icons.TwoTone.DarkMode,
                                onValueChange = { newValue ->
                                    when (newValue) {
                                        ThemeSwitchValue.DARK -> {
                                            vm.setDarkThemeEnabled(true)
                                        }
                                        ThemeSwitchValue.LIGHT -> {
                                            vm.setDarkThemeEnabled(false)
                                        }
                                    }
                                }
                            )
                        }
                    }
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
