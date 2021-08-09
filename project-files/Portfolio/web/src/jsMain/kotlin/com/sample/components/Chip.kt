package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.AppCSSVariables
import com.sample.style.WtTexts
import com.sample.style.marginRight
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

@Composable
fun Chip(
    src: String,
    text: String,
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Center)
                padding(4.px)
                backgroundColor(AppCSSVariables.colorChipBg.value())
                borderRadius(9999.px)
            }
        }
    ) {
        Img(
            src = src,
            attrs = {
                style {
                    width(16.px)
                    height(16.px)
                }
            }
        )

        Div(
            attrs = {
                classes(WtTexts.wtCaption)
                style {
                    marginLeft(8.px)
                    marginRight(4.px)
                }
            }
        ) {
            Text(text)
        }
    }
}