package com.sample.style

import org.jetbrains.compose.web.css.*

object WtContent : StyleSheet(AppStylesheet) {
    val jobTitle by style {
        marginLeft(6.px)
        marginTop(16.px)

        media(maxWidth(750.px)) {
            self style {
                marginLeft(4.px)
                marginTop(8.px)
            }
        }
    }

    val sectionHeaderHr by style {
        marginTop(12.px)
        width(72.px)
        border {
            width = 2.px
            style = LineStyle.Solid
            color = AppCSSVariables.colorPrimary.value()
        }
        borderRadius(2.px)

        media(maxWidth(750.px)) {
            self style {
                marginTop(6.px)
                width(48.px)
                border {
                    width = 1.px
                    style = LineStyle.Solid
                    color = AppCSSVariables.colorPrimary.value()
                }
                borderRadius(1.px)
            }
        }
    }

    val projectRightPadding by style {
        paddingRight(8.px)

        media(maxWidth(750.px)) {
            self style {
                padding(0.px)
            }
        }
    }

    val projectLeftPadding by style {
        paddingLeft(8.px)

        media(maxWidth(750.px)) {
            self style {
                padding(0.px)
            }
        }
    }

    val projectBottomPadding by style {
        paddingBottom(36.px)

        media(maxWidth(750.px)) {
            self style {
                paddingBottom(36.px)
            }
        }
    }

    val sectionDividerHr by style {
        border {
            width = 2.px
            style = LineStyle.Solid
            color = AppCSSVariables.colorHr.value()
        }
        borderRadius(2.px)

        media(maxWidth(750.px)) {
            self style {
                border {
                    width = 1.px
                    style = LineStyle.Solid
                    color = AppCSSVariables.colorHr.value()
                }
                borderRadius(1.px)
            }
        }
    }
}