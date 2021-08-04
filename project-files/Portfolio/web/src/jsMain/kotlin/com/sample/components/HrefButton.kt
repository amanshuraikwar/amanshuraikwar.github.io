package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.*
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement

@Composable
fun HrefButton(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    iconUrl: String,
    link: String,
    text: String,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            classes(

            )
        }
    ) {
        A(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                }
                classes(
                    WtTexts.wtLink,
                )
                style {
                    width(100.percent)
                }
                target(ATarget.Blank)
            },
            href = link
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
                    src = iconUrl,
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
                            marginLeft(16.px)
                        }
                    }
                ) {
                    Text(text)
                }
            }
        }
    }

}

@Composable
fun HrefButton(
    link: String,
    text: String,
) {
    A(
        attrs = {
            classes(
                WtTexts.wtButton,
                WtOffsets.wtRightOffset24,
            )
            target(ATarget.Blank)
        },
        href = link
    ) {
        Text(text)
    }
}

@Composable
fun HrefButton(
    link: String,
    fillWidth: Boolean = false,
    content: ContentBuilder<HTMLAnchorElement>
) {
    A(
        attrs = {
            classes(
                WtTexts.wtButton,
                WtOffsets.wtRightOffset24,
            )
            target(ATarget.Blank)
            if (fillWidth) {
                style {
                    width(100.percent)
                }
            }
        },
        href = link,
        content = content
    )
}

@Composable
fun Button(
    fillWidth: Boolean = false,
    onClick: () -> Unit,
    content: ContentBuilder<HTMLAnchorElement>,
) {
    A(
        attrs = {
            classes(
                WtTexts.wtButton,
                WtOffsets.wtRightOffset24,
            )
            target(ATarget.Blank)
            if (fillWidth) {
                style {
                    width(100.percent)
                }
            }
            onClick {
                onClick()
            }
            style {
                property("cursor", "pointer")
            }
        },
        content = content
    )
}