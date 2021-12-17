package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.AppCSSVariables
import com.sample.style.WtButton
import com.sample.style.WtTexts
import com.sample.style.paddingLeft
import com.sample.style.paddingRight
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.css.CSS

enum class ButtonStyle {
    SOLID,
    OUTLINE
}

enum class ButtonSize {
    NORMAL,
    SMALL
}

@Composable
fun ImgButton(
    src: String,
    onClick: () -> Unit,
    buttonStyle: ButtonStyle = ButtonStyle.SOLID,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
) {
    Img(
        src = src,
        attrs = {
            when (buttonStyle) {
                ButtonStyle.SOLID -> classes(WtButton.wtButtonSolid)
                ButtonStyle.OUTLINE -> classes(WtButton.wtButtonOutline)
            }
            when (buttonSize) {
                ButtonSize.NORMAL -> classes(WtButton.wtImgButtonNormal)
                ButtonSize.SMALL -> classes(WtButton.wtImgButtonSmall)
            }
            onClick { onClick() }
        }
    )
}

@Composable
fun ThemeColorButton(
    color: CSSColorValue,
    borderColor: CSSColorValue,
    onClick: () -> Unit,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
) {
    Div(
        attrs = {
            style {
                backgroundColor(color)
            }
            classes(WtButton.wtButtonSolid)
            when (buttonSize) {
                ButtonSize.NORMAL -> classes(WtButton.wtImgButtonNormal)
                ButtonSize.SMALL -> classes(WtButton.wtImgButtonSmall)
            }
            style {
                border {
                    width = 2.px
                    style = LineStyle.Solid
                    color(borderColor)
                }
            }
            onClick { onClick() }
        }
    )
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    buttonStyle: ButtonStyle = ButtonStyle.SOLID,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
) {
    Div(
        attrs = {
            classes(WtTexts.wtButton)
            when (buttonStyle) {
                ButtonStyle.SOLID -> classes(WtButton.wtButtonSolid)
                ButtonStyle.OUTLINE -> classes(WtButton.wtButtonOutline)
            }
            when (buttonSize) {
                ButtonSize.NORMAL -> classes(WtButton.wtTextButtonNormal)
                ButtonSize.SMALL -> classes(WtButton.wtTextButtonSmall)
            }
            onClick { onClick() }
        }
    ) {
        Text(text)
    }
}

@Composable
fun ImgHrefButton(
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    src: String,
    href: String,
    buttonStyle: ButtonStyle = ButtonStyle.SOLID,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
) {
    A(
        attrs = {
            attrs?.invoke(this)
            classes()
            target(ATarget.Blank)
        },
        href = href,
    ) {
        Img(
            src = src,
            attrs = {
                when (buttonStyle) {
                    ButtonStyle.SOLID -> classes(WtButton.wtButtonSolid)
                    ButtonStyle.OUTLINE -> classes(WtButton.wtButtonOutline)
                }
                when (buttonSize) {
                    ButtonSize.NORMAL -> classes(WtButton.wtImgButtonNormal)
                    ButtonSize.SMALL -> classes(WtButton.wtImgButtonSmall)
                }
            }
        )
    }
}

@Composable
fun TextHrefButton(
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    text: String,
    href: String,
    buttonStyle: ButtonStyle = ButtonStyle.SOLID,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
    openInNewTab: Boolean = true
) {
    A(
        attrs = {
            attrs?.invoke(this)
            classes(
                WtTexts.wtButton
            )
            if (openInNewTab) {
                target(ATarget.Blank)
            }
            style {
                color(AppCSSVariables.colorOnPrimary.value())
            }
        },
        href = href,
    ) {
        Div(
            attrs = {
                classes(WtTexts.wtButton)
                when (buttonStyle) {
                    ButtonStyle.SOLID -> classes(WtButton.wtButtonSolid)
                    ButtonStyle.OUTLINE -> classes(WtButton.wtButtonOutline)
                }
                when (buttonSize) {
                    ButtonSize.NORMAL -> classes(WtButton.wtTextButtonNormal)
                    ButtonSize.SMALL -> classes(WtButton.wtTextButtonSmall)
                }
                style {
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.Center)
                    justifyContent(JustifyContent.Center)
                }
            }
        ) {
            Text(text.uppercase())
        }
    }
}

@Composable
fun HeroTextHrefButton(
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    text: String,
    href: String,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
    openInNewTab: Boolean = true
) {
    A(
        attrs = {
            attrs?.invoke(this)
            classes(
                WtTexts.wtButton
            )
            if (openInNewTab) {
                target(ATarget.Blank)
            }
            style {
                color(AppCSSVariables.colorOnPrimary.value())
            }
        },
        href = href,
    ) {
        Div(
            attrs = {
                classes(WtTexts.wtButton)
                classes(WtButton.wtButtonHero)
                when (buttonSize) {
                    ButtonSize.NORMAL -> classes(WtButton.wtTextButtonNormal)
                    ButtonSize.SMALL -> classes(WtButton.wtTextButtonSmall)
                }
                style {
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.FlexStart)
                    justifyContent(JustifyContent.FlexStart)
                }
            }
        ) {
            Text(text.uppercase())
        }
    }
}

@Composable
fun HeroTextHrefButtonSmall(
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    text: String,
    href: String,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
    openInNewTab: Boolean = true
) {
    A(
        attrs = {
            attrs?.invoke(this)
            classes(
                WtTexts.wtButton
            )
            if (openInNewTab) {
                target(ATarget.Blank)
            }
            style {
                color(AppCSSVariables.colorOnPrimary.value())
            }
        },
        href = href,
    ) {
        Div(
            attrs = {
                classes(WtTexts.wtButton)
                classes(WtButton.wtButtonHero)
                when (buttonSize) {
                    ButtonSize.NORMAL -> classes(WtButton.wtTextButtonNormal)
                    ButtonSize.SMALL -> classes(WtButton.wtTextButtonSmall)
                }
                style {
                    borderRadius(0.px)
                    paddingLeft(0.px)
                    paddingRight(0.px)
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.FlexStart)
                    justifyContent(JustifyContent.FlexStart)
                }
            }
        ) {
            Text(text.uppercase())
        }
    }
}

@Composable
fun HeroImgButton(
    src: String,
    onClick: () -> Unit,
    buttonSize: ButtonSize = ButtonSize.NORMAL,
) {
    Img(
        src = src,
        attrs = {
            classes(WtButton.wtButtonHero)
            when (buttonSize) {
                ButtonSize.NORMAL -> classes(WtButton.wtImgButtonNormal)
                ButtonSize.SMALL -> classes(WtButton.wtImgButtonSmall)
            }
            onClick { onClick() }
        }
    )
}