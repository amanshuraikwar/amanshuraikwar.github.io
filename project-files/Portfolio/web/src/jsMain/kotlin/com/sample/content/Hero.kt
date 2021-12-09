package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.ButtonSize
import com.sample.components.ButtonStyle
import com.sample.components.TextHrefButton
import com.sample.style.AppCSSVariables
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun Hero(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    name: String,
) {
    Div(
        attrs = {
            classes(WtContainer.wtContainerWide)
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
                    attrs?.invoke(this)
                    classes(WtContainer.wtContainerHero)
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
                        Text(name)
                    }
                }

                Div(
                    attrs = {
                        classes(WtOffsets.wtTopOffsetSm16, WtContainer.wtContainerHeroLinks)
                    }
                ) {
                    TextHrefButton(
                        text = "Projects",
                        href = "",
                        buttonStyle = ButtonStyle.OUTLINE,
                        buttonSize = ButtonSize.SMALL
                    )

                    TextHrefButton(
                        text = "Experience",
                        href = "",
                        buttonStyle = ButtonStyle.OUTLINE,
                        buttonSize = ButtonSize.SMALL
                    )

                    TextHrefButton(
                        text = "About Me",
                        href = "",
                        buttonStyle = ButtonStyle.OUTLINE,
                        buttonSize = ButtonSize.SMALL
                    )
                }
            }
        }
    }
}