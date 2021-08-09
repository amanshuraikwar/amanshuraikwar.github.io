package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.WtContent
import com.sample.style.WtTexts
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

@Composable
fun SectionHeader(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    title: String,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            classes(WtTexts.wtH4)
        }
    ) {
        P {
            Text(title)
        }

        Hr(
            attrs = {
                classes(WtContent.sectionHeaderHr)
            }
        )
    }
}