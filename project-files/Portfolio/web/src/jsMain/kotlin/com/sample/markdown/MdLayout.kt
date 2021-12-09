package com.sample.markdown

import androidx.compose.runtime.Composable
import com.sample.components.TextHrefButton
import com.sample.style.AppCSSVariables
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun MdLayout(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    nodes: List<MdNode>,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            style {}
        }
    ) {
        nodes.forEach { node ->
            when (node) {
                is MdNode.H1 -> {
                    H1(
                        attrs = {
                            classes(WtTexts.wtH1)
                            style {
                                color(AppCSSVariables.colorPrimary.value())
                            }
                        }
                    ) {
                        Text(node.text)
                    }
                }
                is MdNode.H3 -> {
                    Div(
                        attrs = {
                            classes(
                                WtOffsets.wtTopOffset96,
                                WtOffsets.wtTopOffsetSm48,
                            )
                        }
                    )

                    H3(
                        attrs = {
                            classes(WtTexts.wtH3)
                            style {
                                color(AppCSSVariables.colorOnBackground.value())
                            }
                        }
                    ) {
                        Text(node.text)
                    }
                }
                is MdNode.H5 -> {
                    H5(
                        attrs = {
                            classes(WtTexts.wtH5)
                            style {
                                color(AppCSSVariables.colorOnBackground.value())
                            }
                        }
                    ) {
                        Text(node.text)
                    }
                }
                is MdNode.P -> {
                    Div(
                        attrs = {
                            classes(
                                WtOffsets.wtTopOffset24,
                                WtOffsets.wtTopOffsetSm16,
                            )
                        }
                    ) {
                        P(
                            attrs = {
                                classes(
                                    WtTexts.wtBody,
                                )
                                style {
                                    color(AppCSSVariables.colorOnBackground.value())
                                }
                            }
                        ) {
                            Text(node.text)
                        }
                    }
                }
                is MdNode.Img -> {
                    Div(
                        attrs = {

                            style {
                                display(DisplayStyle.Flex)
                                flexDirection(FlexDirection.Column)
                                justifyContent(JustifyContent.Center)
                                alignItems(AlignItems.Center)
                            }
                            classes(
                                WtOffsets.wtTopOffset48,
                                WtOffsets.wtTopOffsetSm24,
                            )
                        }
                    ) {
                        Img(
                            src = node.url,
                            attrs = {
                                style {
                                    // to fix the weird image stretch safari issue
                                    // https://github.com/neptunian/react-photo-gallery/issues/174
                                    width(100.percent)
                                }
                            }
                        )

                        Div(
                            attrs = {
                                classes(WtTexts.wtBody)
                                classes(
                                    WtOffsets.wtTopOffset16,
                                )
                                style {
                                    color(AppCSSVariables.colorOnBackgroundSecondary.value())
                                }
                            }
                        ) {
                            Text(node.label)
                        }
                    }
                }
                is MdNode.Spacer -> {
//                    Div(
//                        attrs = {
//                            classes(
//                                WtOffsets.wtTopOffset48,
//                                WtOffsets.wtTopOffsetSm24,
//                            )
//                        }
//                    )
                }
                is MdNode.Date -> {
                    Div(
                        attrs = {

                            style {
                                display(DisplayStyle.Flex)
                                justifyContent(JustifyContent.FlexStart)
                                alignItems(AlignItems.FlexStart)
                            }
                            classes(
                                WtOffsets.wtTopOffset16,
                            )
                        }
                    ) {
                        H6(
                            attrs = {
                                classes(WtTexts.wtH6)
                                style {
                                    color(AppCSSVariables.colorOnBackgroundSecondary.value())
                                }
                            }
                        ) {
                            Text(node.text)
                        }
                    }
                }
                is MdNode.Btn -> {
                    Div(
                        attrs = {

                            style {
                                display(DisplayStyle.Flex)
                                flexDirection(FlexDirection.Column)
                                justifyContent(JustifyContent.Center)
                                alignItems(AlignItems.Center)
                            }
                            classes(
                                WtOffsets.wtTopOffset48,
                                WtOffsets.wtTopOffsetSm24,
                            )
                        }
                    ) {
                        TextHrefButton(
                            text = node.text,
                            href = node.url,
                        )
                    }
                }
            }
        }
    }
}