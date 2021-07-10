package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Text

@Composable
fun HrefButton(
    link: String,
    text: String
) {
    A(
        attrs = {
            classes(
                WtTexts.wtButton,
                WtOffsets.wtTopOffset24,
                WtOffsets.wtRightOffset24,
                WtOffsets.wtTopOffsetSm12,
            )
            target(ATarget.Blank)
        },
        href = link
    ) {
        Text(text)
    }
}