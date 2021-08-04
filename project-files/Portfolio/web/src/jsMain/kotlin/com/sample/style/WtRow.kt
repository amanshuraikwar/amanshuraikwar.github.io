package com.sample.style

import org.jetbrains.compose.web.css.*

object WtRows : StyleSheet(AppStylesheet) {

    val wtRow by style {
        AppCSSVariables.wtHorizontalLayoutGutter(0.px)
        display(DisplayStyle.Flex)
        flexWrap(FlexWrap.Wrap)
        property("box-sizing", "border-box")
    }

    val wtRowSizeM by style {
    }

    val wtRowSizeXs by style {
        AppCSSVariables.wtHorizontalLayoutGutter(6.px)
    }

    val wtRowSmAlignItemsCenter by style {
        self style {
            alignItems(AlignItems.Center)
        }
    }
}