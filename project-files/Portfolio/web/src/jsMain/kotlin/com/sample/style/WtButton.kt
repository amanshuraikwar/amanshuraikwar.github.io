package com.sample.style

import org.jetbrains.compose.web.css.*

object WtButton : StyleSheet(AppStylesheet) {
    val wtButtonHero by style {
        color(AppCSSVariables.colorOnBackground.value())
        transition("all 0.2s")

        hover(self) style {
            property("transform", "scale(1.02)")
            backgroundColor(AppCSSVariables.outlineBtnBgHover.value())
        }


        self + active style {
            property("transform", "scale(0.98)")
            backgroundColor(AppCSSVariables.outlineBtnBgHover.value())
        }
    }

    val wtButtonSolid by style {
        backgroundColor(AppCSSVariables.colorPrimary.value())
        color(AppCSSVariables.colorOnPrimary.value())
        transition("all 0.2s")

        hover(self) style {
            property("transform", "scale(1.2)")
        }


        self + active style {
            property("transform", "scale(0.98)")
        }
    }

    val wtButtonOutline by style {
        border {
            width = 2.px
            style = LineStyle.Solid
            color = AppCSSVariables.colorOnBackground.value()
        }
        color(AppCSSVariables.colorOnBackground.value())
        transition("all 0.2s")

        hover(self) style {
            property("transform", "scale(1.2)")
            backgroundColor(AppCSSVariables.outlineBtnBgHover.value())
        }

        self + active style {
            property("transform", "scale(0.98)")
            backgroundColor(AppCSSVariables.outlineBtnBgActive.value())
        }
    }

    val wtTextButtonNormal by style {
        padding(16.px)
        borderRadius(16.px)
        cursor("pointer")

        media(mediaMaxWidth(750.px)) {
            self style {
                padding(12.px)
                borderRadius(12.px)
            }
        }
    }

    val wtTextButtonSmall by style {
        padding(12.px)
        borderRadius(12.px)
        cursor("pointer")
    }

    val wtImgButtonNormal by style {
        width(24.px)
        height(24.px)
        padding(12.px)
        borderRadius(16.px)
        cursor("pointer")

        media(mediaMaxWidth(750.px)) {
            self style {
                padding(8.px)
                width(20.px)
                height(20.px)
                borderRadius(12.px)
            }
        }
    }

    val wtImgButtonSmall by style {
        padding(8.px)
        width(20.px)
        height(20.px)
        borderRadius(12.px)
        cursor("pointer")
    }

    val wtButtonBlog by style {
        transition("all 0.2s")

        hover(self) style {
            backgroundColor(AppCSSVariables.outlineBtnBgHover.value())
            property("transform", "scale(1.02)")
        }


        self + active style {
            backgroundColor(AppCSSVariables.outlineBtnBgActive.value())
            property("transform", "scale(0.98)")
        }
    }
}