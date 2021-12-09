package com.sample.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sample.components.ButtonSize
import com.sample.components.ButtonStyle
import com.sample.components.ImgButton
import com.sample.components.TextHrefButton
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
import org.jetbrains.compose.web.css.backgroundColor
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
                backgroundColor(AppCSSVariables.colorHeroBg.value())
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
                    H1(
                        attrs = {
                            classes(WtTexts.wtHero)
                            style {
                                color(AppCSSVariables.colorPrimary.value())
                            }
                        }
                    ) {
                        A(
                            attrs = {
                                style {}
                            },
                            href = "https://amanshuraikwar.github.io/",
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
                    TextHrefButton(
                        text = "Projects",
                        href = "https://amanshuraikwar.github.io/projects/",
                        buttonStyle = ButtonStyle.OUTLINE,
                        buttonSize = ButtonSize.SMALL,
                        openInNewTab = false
                    )

                    TextHrefButton(
                        text = "My Background",
                        href = "https://amanshuraikwar.github.io/background/",
                        buttonStyle = ButtonStyle.OUTLINE,
                        buttonSize = ButtonSize.SMALL,
                        openInNewTab = false
                    )

                    TextHrefButton(
                        text = "About Me",
                        href = "https://amanshuraikwar.github.io/me",
                        buttonStyle = ButtonStyle.OUTLINE,
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
                                color(AppCSSVariables.colorPrimary.value())
                                display(DisplayStyle.Flex)
                                flexDirection(FlexDirection.Row)
                                justifyContent(JustifyContent.SpaceBetween)
                                alignItems(AlignItems.Center)
                            }
                        }
                    ) {
                        A(
                            attrs = {
                                style {}
                            },
                            href = "https://amanshuraikwar.github.io/",
                        ) {
                            Div {
                                Text(name)
                            }
                        }

                        ImgButton(
                            if (displayLinks) {
                                if (isDarkTheme) {
                                    "assets/expand_less_black_24dp.svg"
                                } else {
                                    "assets/expand_less_white_24dp.svg"
                                }
                            } else {
                                if (isDarkTheme) {
                                    "assets/expand_more_black_24dp.svg"
                                } else {
                                    "assets/expand_more_white_24dp.svg"
                                }
                            },
                            onClick = {
                                displayLinks = !displayLinks
                            },
                            buttonStyle = ButtonStyle.OUTLINE,
                            buttonSize = ButtonSize.SMALL
                        )
                    }
                }

                if (displayLinks) {
                    Div(
                        attrs = {
                            classes(
                                WtOffsets.wtTopOffsetSm24,
                                WtContainer.wtContainerHeroLinks
                            )
                        }
                    ) {
                        TextHrefButton(
                            text = "Projects",
                            href = "https://amanshuraikwar.github.io/projects/",
                            buttonStyle = ButtonStyle.OUTLINE,
                            buttonSize = ButtonSize.SMALL,
                            openInNewTab = false
                        )

                        TextHrefButton(
                            text = "My Background",
                            href = "https://amanshuraikwar.github.io/background/",
                            buttonStyle = ButtonStyle.OUTLINE,
                            buttonSize = ButtonSize.SMALL,
                            openInNewTab = false
                        )

                        TextHrefButton(
                            text = "About Me",
                            href = "https://amanshuraikwar.github.io/me",
                            buttonStyle = ButtonStyle.OUTLINE,
                            buttonSize = ButtonSize.SMALL,
                            openInNewTab = false
                        )
                    }
                }
            }
        }
    }
}