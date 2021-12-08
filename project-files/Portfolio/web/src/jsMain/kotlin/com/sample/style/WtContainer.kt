package com.sample.style

import org.jetbrains.compose.web.css.*

object WtContainer : StyleSheet(AppStylesheet) {
    val wtContainer by style {
        property("margin-left", "auto")
        property("margin-right", "auto")
        property("box-sizing", "border-box")
        property("padding-left", 22.px)
        property("padding-right", 22.px)
        property("max-width", 750.px)

        media(mediaMaxWidth(750.px)) {
            self style {
                property("max-width", 100.percent)
                property("padding-left", 48.px)
                property("padding-right", 48.px)
            }
        }
    }

    val wtContainerSm by style {
        property("margin-left", "auto")
        property("margin-right", "auto")
        property("box-sizing", "border-box")
        property("padding-left", 12.px)
        property("padding-right", 12.px)
        property("max-width", 750.px)
        property("width", 750.px)

        media(mediaMaxWidth(750.px)) {
            self style {
                property("max-width", 100.percent)
                property("padding-left", 12.px)
                property("padding-right", 12.px)
            }
        }
    }
}