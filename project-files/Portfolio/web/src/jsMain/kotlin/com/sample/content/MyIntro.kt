package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun MyIntro(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    intro: String,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            classes(
                WtTexts.wtText1,
            )
        }
    ) {
        Text(intro)
    }
}