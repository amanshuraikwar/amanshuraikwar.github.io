package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.FontWeight
import com.sample.style.WtTexts
import com.sample.style.fontWeight
import io.github.amanshuraikwar.portfolio.model.ExperienceData
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun ExperienceItem(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    data: ExperienceData,
    isDarkTheme: Boolean
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.FlexStart)
            }
        }
    ) {
        Div(
            attrs = {
                classes(WtTexts.wtH5)
                style {
                    fontWeight(FontWeight.Bold)
                }
            }
        ) {
            Text(data.title)
        }

        Div(
            attrs = {
                classes(WtTexts.wtBody)
                style {
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.Center)
                    marginTop(16.px)
                }
            }
        ) {
            Img(
                src = if (isDarkTheme) {
                    "assets/place_white_24.svg"
                } else {
                    "assets/place_black_24.svg"
                },
                attrs = {
                    style {
                        width(18.px)
                        height(18.px)
                    }
                }
            )

            Div(
                attrs = {
                    style {
                        marginLeft(12.px)
                    }
                }
            ) {
                Text(
                    data.location
                )
            }
        }

        Div(
            attrs = {
                classes(WtTexts.wtBody)
                style {
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.Center)
                    marginTop(16.px)
                }
            }
        ) {
            Img(
                src = if (isDarkTheme) {
                    "assets/date_range_white_24.svg"
                } else {
                    "assets/date_range_black_24.svg"
                },
                attrs = {
                    style {
                        width(18.px)
                        height(18.px)
                    }
                }
            )

            Div(
                attrs = {
                    style {
                        marginLeft(12.px)
                    }
                }
            ) {
                Text(
                    data.dateRange
                )
            }
        }

        Div(
            attrs = {
                classes(WtTexts.wtBody)
                style {
                    marginTop(16.px)
                }
            }
        ) {
            data.content.forEachIndexed { index, contentPoint ->
                Div(
                    attrs = {
                        style {
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.FlexStart)
                            if (index != 0) {
                                marginTop(8.px)
                            }
                        }
                    }
                ) {
                    Img(
                        src = if (isDarkTheme) {
                            "assets/arrow_right_alt_white_24.svg"
                        } else {
                            "assets/arrow_right_alt_black_24.svg"
                        },
                        attrs = {
                            style {
                                width(18.px)
                                height(18.px)
                            }
                        }
                    )

                    Div(
                        attrs = {
                            style {
                                marginLeft(12.px)
                            }
                        }
                    ) {
                        Text(
                            contentPoint
                        )
                    }
                }
            }
        }
    }
}