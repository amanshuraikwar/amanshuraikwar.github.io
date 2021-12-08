package com.sample.style

import org.jetbrains.compose.web.css.*

object WtOffsets : StyleSheet(AppStylesheet) {
    val wtTopOffset96 by style {
        marginTop(96.px)
//        property(
//            "margin-top",
//            "calc(4*${AppCSSVariables.wtOffsetTopUnit.value(24.px)})"
//        )
    }

    val wtTopOffset24 by style {
        marginTop(24.px)
//        property(
//            "margin-top",
//            "calc(1*${AppCSSVariables.wtOffsetTopUnit.value(24.px)})"
//        )
    }

    val wtTopOffset16 by style {
        marginTop(16.px)
//        property(
//            "margin-top",
//            "calc(1*${AppCSSVariables.wtOffsetTopUnit.value(16.px)})"
//        )
    }

    val wtRightOffset24 by style {
        marginTop(24.px)
//        property(
//            "margin-right",
//            "calc(1*${AppCSSVariables.wtOffsetTopUnit.value(24.px)})"
//        )
    }

    val wtTopOffset48 by style {
        marginTop(48.px)
//        property(
//            "margin-top",
//            "calc(1*${AppCSSVariables.wtOffsetTopUnit.value(48.px)})"
//        )
    }

    val wtTopOffsetSm12 by style {
        media(mediaMaxWidth(750.px)) {
            self style {
                marginTop(12.px)
            }
        }
    }

    val wtTopOffsetSm8 by style {
        media(mediaMaxWidth(750.px)) {
            self style {
                marginTop(8.px)
            }
        }
    }


    val wtTopOffsetSm16 by style {
        media(mediaMaxWidth(750.px)) {
            self style {
                marginTop(16.px)
            }
        }
    }

    val wtTopOffsetSm24 by style {
        media(mediaMaxWidth(750.px)) {
            self style {
                marginTop(36.px)
            }
        }
    }

    val wtTopOffsetSm48 by style {
        media(mediaMaxWidth(750.px)) {
            self style {
                marginTop(48.px)
            }
        }
    }
}