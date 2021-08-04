package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.AppCSSVariables
import com.sample.style.WtPadding
import com.sample.style.WtRadius
import com.sample.style.WtTexts
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

@Composable
fun ThemeSwitch(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    isDarkTheme: Boolean,
    onThemeChange: (isDarkTheme: Boolean) -> Unit,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            classes(
                WtTexts.wtLink,
            )
            style {
                display(DisplayStyle.Flex)
                property("cursor", "pointer")
            }
            onClick {
                onThemeChange(!isDarkTheme)
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    property("flex", 1)
                    alignItems(AlignItems.Center)
                    classes(
                        WtTexts.wtButton,
                        WtRadius.wtRadius16,
                        WtRadius.wtRadius8Sm,
                        WtPadding.wtPadding8,
                        WtPadding.wtPaddingSm8,
                    )
                }


            }
        ) {
            Img(
                src = if (isDarkTheme) {
                    "assets/dark_mode_black_24.svg"
                } else {
                    "assets/light_mode_white_24.svg"
                },
                attrs = {
                    style {
                        width(24.px)
                        height(24.px)
                        padding(8.px)
                        property(
                            "box-shadow",
                            "0.5px 0.5px 4px 2px rgba(0, 0, 0, 0.2)"
                        )
                        backgroundColor(AppCSSVariables.colorPrimary.value())
                    }
                    classes(
                        WtRadius.wtRadius12,
                        WtRadius.wtRadius8Sm,
                    )
                }
            )

            Span(
                attrs = {
                    style {
                        property("flex", 1)
                        marginLeft(16.px)
                    }
                }
            ) {
                Text("Dark Mode")
            }

            Div(
                attrs = {
                    style {
                        width(56.px)
                        height(28.px)
                        padding(4.px)
                        backgroundColor(AppCSSVariables.colorPrimary.value())
                        display(DisplayStyle.Flex)
                    }

                    classes(
                        WtRadius.wtRadius12,
                        WtRadius.wtRadius8Sm,
                    )
                }
            ) {
                if (isDarkTheme) {
                    Div(
                        attrs = {
                            style {
                                property("flex", 1)
                            }
                        }
                    ) {

                    }

                    Div(
                        attrs = {
                            style {
                                property("flex", 1)
                                backgroundColor(AppCSSVariables.colorOnPrimary.value())
                            }

                            classes(
                                WtRadius.wtRadius8,
                                WtRadius.wtRadius4Sm,
                            )
                        }
                    ) {

                    }
                } else {
                    Div(
                        attrs = {
                            style {
                                property("flex", 1)
                                backgroundColor(AppCSSVariables.colorOnPrimary.value())
                            }

                            classes(
                                WtRadius.wtRadius8,
                                WtRadius.wtRadius4Sm,
                            )
                        }
                    ) {

                    }

                    Div(
                        attrs = {
                            style {
                                property("flex", 1)
                            }
                        }
                    ) {

                    }
                }
            }
        }
    }
}