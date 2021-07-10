package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.AppCSSVariables
import com.sample.style.WtTexts
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun MyName(
    name: String,
) {
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