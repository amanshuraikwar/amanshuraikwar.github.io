package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.style.AppCSSVariables
import com.sample.style.WtContent
import com.sample.style.WtTexts
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

@Composable
fun Hero(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    name: String,
    jobTitle: String,
    isDarkTheme: Boolean,
    onThemeBtnClick: (isDarkTheme: Boolean) -> Unit,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceBetween)
            }
        }
    ) {
        Div {
            H1(
                attrs = {
                    classes(WtTexts.wtH1)
                    style {
                        color(AppCSSVariables.colorPrimary.value())
                    }
                }
            ) {
                Text(name)
            }

            Div(
                attrs = {
                    classes(
                        WtTexts.wtBody,
                        WtContent.jobTitle
                    )
                    style {
                        color(AppCSSVariables.colorOnBackgroundSecondary.value())
                    }
                }
            ) {
                Text(jobTitle)
            }
        }

        ThemeSwitch(
            isDarkTheme,
            onThemeBtnClick
        )
    }
}