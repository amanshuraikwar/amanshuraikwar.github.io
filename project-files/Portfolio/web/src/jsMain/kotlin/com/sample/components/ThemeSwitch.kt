package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.dropAlphaHex
import io.github.amanshuraikwar.portfolio.model.ThemeData
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

@Composable
fun ThemeSwitch(
    themeColorsName: String,
    themeData: ThemeData,
    onThemeChange: (selectedThemeColorsName: String) -> Unit,
) {
    Div(
        {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
                gap(12.px)
            }
        }
    ) {
        themeData.themes.forEach {
            ThemeColorButton(
                color = Color(it.primaryColor.dropAlphaHex()),
                borderColor = Color(it.onPrimaryColor.dropAlphaHex()),
                onClick = {
                    onThemeChange(it.name)
                },
                buttonSize = if (it.name == themeColorsName) {
                    ButtonSize.NORMAL
                } else {
                    ButtonSize.SMALL
                }
            )
        }
    }
}