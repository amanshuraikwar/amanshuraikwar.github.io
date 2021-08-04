package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.AppCSSVariables
import com.sample.style.WtTexts
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLHeadingElement

@Composable
fun MyName(
    attrs: AttrBuilderContext<HTMLHeadingElement>? = null,
    name: String,
) {
    H1(
        attrs = {
            attrs?.invoke(this)
            classes(WtTexts.wtHero)
            style {
                color(AppCSSVariables.colorPrimary.value())
            }
        }
    ) {
        Text(name)
    }
}