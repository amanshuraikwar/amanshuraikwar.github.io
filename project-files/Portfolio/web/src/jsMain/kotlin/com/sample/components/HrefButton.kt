package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLAnchorElement

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