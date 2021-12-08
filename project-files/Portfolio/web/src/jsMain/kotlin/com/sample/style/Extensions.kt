package com.sample.style

import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.StyleBuilder

fun StyleBuilder.flex(value: Int) {
    property("flex", value)
}

fun StyleBuilder.fontWeight(value: Int) {
    property("font-weight", value)
}

fun StyleBuilder.lineHeight(value: CSSNumeric) {
    property("line-height", value)
}

fun StyleBuilder.fontFamily(value: String) {
    property("font-family", value)
}

fun StyleBuilder.paddingBottom(value: CSSNumeric) {
    property("padding-bottom", value)
}

fun StyleBuilder.cursor(value: String) {
    property("cursor", value)
}

fun StyleBuilder.marginRight(value: CSSNumeric) {
    property("margin-right", value)
}

fun StyleBuilder.paddingRight(value: CSSNumeric) {
    property("padding-right", value)
}

fun StyleBuilder.paddingLeft(value: CSSNumeric) {
    property("padding-left", value)
}

fun StyleBuilder.paddingTop(value: CSSNumeric) {
    property("padding-top", value)
}

fun StyleBuilder.transition(value: String) {
    property("transition", value)
}