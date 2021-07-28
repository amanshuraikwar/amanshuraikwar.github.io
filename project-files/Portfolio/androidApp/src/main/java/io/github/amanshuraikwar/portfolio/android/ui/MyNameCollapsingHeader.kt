package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.Layout
//import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MyNameCollapsingHeader(
    modifier: Modifier = Modifier,
    name: String,
    intro: String,
    isDarkTheme: Boolean,
    onThemeSwitchValueChange: (isDarkThemeEnabled: Boolean) -> Unit,
    // 0f -> collapsed & 1f -> expanded
    expandFraction: Float,
    headerHeight: MutableState<Dp>,
    collapsedHeight: MutableState<Float>,
    expandedHeight: MutableState<Float>,
) {
    Layout(
        modifier = modifier,
        content = {
            FillFirstRow(
                modifier = Modifier
                    .alpha(1f - expandFraction)
                    .padding(
                        vertical = 16.dp,
                        horizontal = 16.dp
                    )
            ) {
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary
                )

                ThemeSwitch(
                    modifier = Modifier,
                    value = if (isDarkTheme) {
                        ThemeSwitchValue.DARK
                    } else {
                        ThemeSwitchValue.LIGHT
                    },
                    lightThemeIcon = Icons.Rounded.LightMode,
                    darkThemeIcon = Icons.Rounded.DarkMode,
                    //avdIcon = animatedVectorResource(id = R.drawable.avd_theme_switch_24),
                    //avdEndValue = ThemeSwitchValue.LIGHT,
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

            Column(
                modifier = Modifier
                    .alpha(expandFraction)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    text = name,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.primary
                )

                FillFirstRow(
                    Modifier
                        .padding(top = 8.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        text = intro,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.subtitle1,
                    )

                    ThemeSwitch(
                        modifier = Modifier,
                        value = if (isDarkTheme) {
                            ThemeSwitchValue.DARK
                        } else {
                            ThemeSwitchValue.LIGHT
                        },
                        lightThemeIcon = Icons.Rounded.LightMode,
                        darkThemeIcon = Icons.Rounded.DarkMode,
                        //avdIcon = animatedVectorResource(id = R.drawable.avd_theme_switch_24),
                        //avdEndValue = ThemeSwitchValue.LIGHT,
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
    ) { measurables, constraints ->
        val collapsedPlaceable =
            measurables[0].measure(constraints = constraints.copy(minHeight = 0))
        val expandedPlaceable =
            measurables[1].measure(constraints = constraints.copy(minHeight = 0))

        collapsedHeight.value = collapsedPlaceable.height.toFloat()
        expandedHeight.value = expandedPlaceable.height.toFloat()

        val height = collapsedPlaceable.height +
                expandFraction * (expandedPlaceable.height - collapsedPlaceable.height)

        headerHeight.value = height.roundToInt().toDp()

        layout(width = constraints.maxWidth, height = height.roundToInt()) {
            collapsedPlaceable.place(
                0,
                0,
                if (expandFraction > 0.5f) 0f else 1f
            )
            expandedPlaceable.place(
                0,
                height.roundToInt() - expandedPlaceable.height,
                if (expandFraction > 0.5f) 1f else 0f
            )
        }
    }
}