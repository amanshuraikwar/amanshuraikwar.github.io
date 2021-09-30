package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.WtButton
import com.sample.style.WtTexts
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLAnchorElement

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