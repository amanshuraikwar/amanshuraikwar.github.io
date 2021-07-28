package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.amanshuraikwar.portfolio.android.ui.FillFirstRow
import io.github.amanshuraikwar.portfolio.android.ui.ThemeSwitch
import io.github.amanshuraikwar.portfolio.android.ui.ThemeSwitchValue

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ThemeSwitchView(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    onThemeSwitchValueChange: (isDarkThemeEnabled: Boolean) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.primary.copy(alpha = 0.08f)
    ) {
        FillFirstRow(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        onThemeSwitchValueChange(!isDarkTheme)
                    }
                )
        ) {
            Row {
                Surface(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.small,
                    elevation = 2.dp,
                    color = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = if (isDarkTheme) {
                            Icons.Rounded.DarkMode
                        } else {
                            Icons.Rounded.LightMode
                        },
                        contentDescription = "App Theme",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    text = "App Theme",
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1
                )
            }

            ThemeSwitch(
                modifier = Modifier.padding(end = 8.dp),
                value = if (isDarkTheme) {
                    ThemeSwitchValue.DARK
                } else {
                    ThemeSwitchValue.LIGHT
                },
                lightThemeIcon = Icons.Rounded.LightMode,
                darkThemeIcon = Icons.Rounded.DarkMode,
                onValueChange = { newValue ->
                    when (newValue) {
                        ThemeSwitchValue.DARK -> {
                            onThemeSwitchValueChange(true)
                        }
                        ThemeSwitchValue.LIGHT -> {
                            onThemeSwitchValueChange(false)
                        }
                    }
                }
            )
        }
    }
}