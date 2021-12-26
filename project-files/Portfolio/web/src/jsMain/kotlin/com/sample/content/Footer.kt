package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.style.AppCSSVariables
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.model.ThemeData
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.flexWrap
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

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

            GithubBuildStatus(
                attrs = {
                    classes(
                        WtOffsets.wtTopOffset24,
                        WtOffsets.wtTopOffsetSm12,
                    )
                }
            )

            Div(
                attrs = {
                    classes(
                        WtOffsets.wtTopOffset96,
                        WtOffsets.wtTopOffsetSm48,
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

@Composable
fun GithubBuildStatus(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.FlexStart)
                alignItems(AlignItems.Center)
                flexWrap(FlexWrap.Wrap)
                gap(12.px)
            }
        }
    ) {
        A(
            href = "https://github.com/amanshuraikwar/amanshuraikwar.github.io/tree/gh-pages",
            {
                style {
                    target(ATarget.Blank)
                }
            }
        ) {
            Img(src = "https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/build-web-html-js-deploy-github-pages.yml/badge.svg?branch=trunk")
        }

        A(
            href = "",
            {
                style {
                    target(ATarget.Blank)
                }
            }
        ) {
            Img(src = "https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/build-web-html-js.yml/badge.svg")
        }

        A(
            href = "https://amanshuraikwar.github.io/tests/shared/android-jvm/",
            {
                style {
                    target(ATarget.Blank)
                }
            }
        ) {
            Img(src = "https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/shared-tests-android-jvm.yml/badge.svg")
        }

        A(
            href = "https://amanshuraikwar.github.io/tests/shared/js-browser/",
            {
                style {
                    target(ATarget.Blank)
                }
            }
        ) {
            Img(src = "https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/shared-tests-js-browser.yml/badge.svg")
        }
    }
}