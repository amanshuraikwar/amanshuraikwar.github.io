package com.sample.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sample.components.ButtonSize
import com.sample.components.HeroImgButton
import com.sample.components.HeroTextHrefButton
import com.sample.style.AppCSSVariables
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun Hero(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    name: String,
    isDarkTheme: Boolean
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            classes(
                WtContainer.wtContainerWide,
            )
            style {
                property(
                    "border-bottom",
                    "2px solid " + AppCSSVariables.colorHr.value().toString()
                )
            }
        }
    ) {
        Div(
            attrs = {
                classes(
                    WtRows.wtRow,
                )
            }
        ) {
            Div(
                attrs = {
                    classes(
                        WtContainer.wtContainerHero,
                    )
                }
            ) {
                Div {
                    A(href = "https://amanshuraikwar.github.io/") {
                        H1(
                            attrs = {
                                classes(WtTexts.wtHero)
                                style {
                                    color(AppCSSVariables.colorOnBackground.value())
                                }
                            }
                        ) {
                            Text(name)
                        }
                    }
                }

                Div(
                    attrs = {
                        classes(WtOffsets.wtTopOffsetSm16, WtContainer.wtContainerHeroLinks)
                    }
                ) {
                    HeroTextHrefButton(
                        text = "Projects",
                        href = "https://amanshuraikwar.github.io/projects/",
                        buttonSize = ButtonSize.SMALL,
                        openInNewTab = false
                    )

                    HeroTextHrefButton(
                        text = "My Background",
                        href = "https://amanshuraikwar.github.io/background/",
                        buttonSize = ButtonSize.SMALL,
                        openInNewTab = false
                    )

                    HeroTextHrefButton(
                        text = "About Me",
                        href = "https://amanshuraikwar.github.io/me",
                        buttonSize = ButtonSize.SMALL,
                        openInNewTab = false
                    )
                }
            }

            Div(
                attrs = {
                    classes(
                        WtContainer.wtContainerHeroSm,
                    )
                }
            ) {
                var displayLinks by remember { mutableStateOf(false) }

                Div {
                    H1(
                        attrs = {
                            classes(WtTexts.wtHero)
                            style {
                                display(DisplayStyle.Flex)
                                flexDirection(FlexDirection.Row)
                                justifyContent(JustifyContent.SpaceBetween)
                                alignItems(AlignItems.Center)
                            }
                        }
                    ) {
                        A(href = "https://amanshuraikwar.github.io/") {
                            Div(
                                attrs = {
                                    style {
                                        color(AppCSSVariables.colorOnBackground.value())
                                    }
                                }
                            ) {
                                Text(name)
                            }
                        }

                        HeroImgButton(
                            if (displayLinks) {
                                if (isDarkTheme) {
                                    "https://amanshuraikwar.github.io/assets/expand_less_white_24dp.svg"
                                } else {
                                    "https://amanshuraikwar.github.io/assets/expand_less_black_24dp.svg"
                                }
                            } else {
                                if (isDarkTheme) {
                                    "https://amanshuraikwar.github.io/assets/expand_more_white_24dp.svg"
                                } else {
                                    "https://amanshuraikwar.github.io/assets/expand_more_black_24dp.svg"
                                }
                            },
                            onClick = {
                                displayLinks = !displayLinks
                            },
                            buttonSize = ButtonSize.SMALL
                        )
                    }
                }

                if (displayLinks) {
                    Div(
                        attrs = {
                            classes(
                                WtOffsets.wtTopOffsetSm16,
                                WtContainer.wtContainerHeroLinks
                            )
                        }
                    ) {
                        HeroTextHrefButton(
                            text = "Projects",
                            href = "https://amanshuraikwar.github.io/projects/",
                            buttonSize = ButtonSize.SMALL,
                            openInNewTab = false
                        )

                        HeroTextHrefButton(
                            text = "My Background",
                            href = "https://amanshuraikwar.github.io/background/",
                            buttonSize = ButtonSize.SMALL,
                            openInNewTab = false
                        )

                        HeroTextHrefButton(
                            text = "About Me",
                            href = "https://amanshuraikwar.github.io/me",
                            buttonSize = ButtonSize.SMALL,
                            openInNewTab = false
                        )
                    }
                }
            }
        }
    }
}