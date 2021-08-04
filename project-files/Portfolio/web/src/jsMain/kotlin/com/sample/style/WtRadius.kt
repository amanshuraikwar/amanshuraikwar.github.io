package com.sample.style

import org.jetbrains.compose.web.css.*

object WtRadius : StyleSheet(AppStylesheet) {
    val wtRadius20 by style {
        borderRadius(20.px)
    }

    val wtRadius16 by style {
        borderRadius(16.px)
    }

    val wtRadius12 by style {
        borderRadius(12.px)
    }

    val wtRadius8 by style {
        borderRadius(8.px)
    }

    val wtRadius8Sm by style {
        media(maxWidth(750.px)) {
            self style {
                borderRadius(8.px)
            }
        }
    }

    val wtRadius4Sm by style {
        media(maxWidth(750.px)) {
            self style {
                borderRadius(4.px)
            }
        }
    }

    val wtRadius12Sm by style {
        media(maxWidth(750.px)) {
            self style {
                borderRadius(12.px)
            }
        }
    }

    val wtRadius16Sm by style {
        media(maxWidth(750.px)) {
            self style {
                borderRadius(16.px)
            }
        }
    }

    val wtRadius20Sm by style {
        media(maxWidth(750.px)) {
            self style {
                borderRadius(20.px)
            }
        }
    }
}