package com.sample.style

import org.jetbrains.compose.web.css.*

object WtPadding : StyleSheet(AppStylesheet) {
    val wtPadding24 by style {
        padding(24.px)
    }

    val wtPadding16 by style {
        padding(16.px)
    }

    val wtPadding8 by style {
        padding(8.px)
    }

    val wtPaddingSm8 by style {
        media(mediaMaxWidth(750.px)) {
            self style {
                padding(8.px)
            }
        }
    }
}