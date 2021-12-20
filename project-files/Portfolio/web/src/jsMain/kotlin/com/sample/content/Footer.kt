package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.style.AppCSSVariables
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.model.ThemeData
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun Footer(
    madeWith: String,
    themeColorsName: String,
    themeData: ThemeData,
    onThemeBtnClick: (selectedThemeColors: String) -> Unit,
) {
    Div(
        {
            classes(
                WtOffsets.wtTopOffset96,
                WtOffsets.wtTopOffsetSm48,
            )
            style {
                background("#212121")
                property(
                    "border-top",
                    "2px solid " + AppCSSVariables.colorHr.value().toString()
                )
            }
        }
    ) {
        Div(
            attrs = {
                classes(WtContainer.wtContainerSm)
            }
        ) {
            Div(
                attrs = {
                    classes(
                        WtOffsets.wtTopOffset96,
                        WtOffsets.wtTopOffsetSm48,
                        WtTexts.wtH3
                    )
                    style {
                        color(Color("#9E9E9E"))
                    }
                }
            ) {
                Text(madeWith)
            }

            Div(
                attrs = {
                    classes(
                        WtOffsets.wtTopOffset48,
                        WtOffsets.wtTopOffsetSm24,
                    )
                    style {
                        display(DisplayStyle.Flex)
                        justifyContent(JustifyContent.FlexEnd)
                        alignItems(AlignItems.Center)
                    }
                }
            ) {
                ThemeSwitch(
                    themeColorsName,
                    themeData,
                    onThemeChange = onThemeBtnClick
                )
            }

            Div(
                attrs = {
                    classes(
                        WtOffsets.wtTopOffset48,
                        WtOffsets.wtTopOffsetSm24,
                    )
                }
            )
        }
    }
}