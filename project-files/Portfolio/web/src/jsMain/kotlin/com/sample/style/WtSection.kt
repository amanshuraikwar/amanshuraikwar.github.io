package com.sample.style

import org.jetbrains.compose.web.css.*

object WtSections : StyleSheet(AppStylesheet) {

    val wtSection by style {
        property("box-sizing", "border-box")
        property("padding-bottom", 96.px)
        property("padding-top", 1.px)
        property(
            propertyName = "padding-bottom",
            value = "calc(4*${AppCSSVariables.wtOffsetTopUnit.value(24.px)})"
        )
        backgroundColor(Color("#fff"))
    }
}