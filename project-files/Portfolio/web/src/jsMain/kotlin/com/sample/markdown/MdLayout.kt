package com.sample.markdown

import androidx.compose.runtime.Composable
import com.sample.HighlightJs
import com.sample.components.TextHrefButton
import com.sample.style.AppCSSVariables
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.Code
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Pre
import org.jetbrains.compose.web.dom.Text

@Composable
fun MdLayout(
    nodes: List<MdNode>,
) {
    nodes.forEach { node ->
        when (node) {
            is MdNode.H1 -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
                    H1(
                        attrs = {
                            classes(WtTexts.wtH1Blog)
                            style {
                                color(AppCSSVariables.colorPrimary.value())
                            }
                        }
                    ) {
                        Text(node.text)
                    }
                }
            }
            is MdNode.H3 -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
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
                            classes(WtTexts.wtH3Blog)
                            style {
                                color(AppCSSVariables.colorOnBackground.value())
                            }
                        }
                    ) {
                        Text(node.text)
                    }
                }
            }
            is MdNode.H5 -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
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
            }
            is MdNode.P -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
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
            }
            is MdNode.Img -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
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
            }
            is MdNode.Spacer -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
                    Div(
                        attrs = {
                            classes(
                                WtOffsets.mdSpacer,
                            )
                        }
                    )
                }
            }
            is MdNode.Date -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
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
                                classes(WtTexts.wtBody)
                                style {
                                    color(AppCSSVariables.colorOnBackgroundSecondary.value())
                                }
                            }
                        ) {
                            B {
                                Text(node.text)
                            }
                        }
                    }
                }
            }
            is MdNode.Btn -> {
                Div(
                    attrs = {
                        classes(
                            WtContainer.wtContainerSm,
                        )
                    }
                ) {
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
            is MdNode.Code -> {
                Pre(
                    attrs = {
                        classes(WtTexts.wtCode)
                        classes(
                            WtOffsets.wtTopOffset48,
                            WtOffsets.wtTopOffsetSm24,
                            WtContainer.wtContainerSmCode,
                        )
                        style {
                            overflow("auto")
                            backgroundColor(AppCSSVariables.outlineBtnBgHover.value())
                            borderRadius(4.px)
                        }
                    }
                ) {
                    Code(
                        attrs = {
                            classes("language-${node.language}", "hljs")
                            style {
                                padding(8.px)
                            }
                        }
                    ) {
                        node.code.split("\n").forEach {
                            Text(it + "\n")
                        }

                        DomSideEffect(node.code) {
                            HighlightJs.highlightElement(it)
                        }
                    }
                }
            }
        }
    }
}