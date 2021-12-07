package com.sample.markdown

import androidx.compose.runtime.Composable
import com.sample.style.AppCSSVariables
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H5
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
            style {
//                display(DisplayStyle.Flex)
//                flexDirection(FlexDirection.Row)
//                justifyContent(JustifyContent.SpaceBetween)
            }
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
                                WtOffsets.wtTopOffset48,
                                WtOffsets.wtTopOffsetSm24,
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
                    )

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
                is MdNode.Img -> {

                }
                is MdNode.Spacer -> {
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
    }
}