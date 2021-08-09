package com.sample.style

import org.jetbrains.compose.web.css.*

object FontWeight {
    const val Thin = 100
    const val ExtraLight = 200
    const val Light = 300
    const val Normal = 400
    const val Medium = 500
    const val SemiBold = 600
    const val Bold = 700
    const val ExtraBold = 800
    const val Black = 900
}

object WtTexts : StyleSheet(AppStylesheet) {
    const val rubikFontStr = "Rubik,Nunito,system-ui,-apple-system,Arial,serif,sans-serif"
    const val nunitoFontStr = "Nunito,Rubik,system-ui,-apple-system,Arial,serif,sans-serif"

    val wtH1 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtH1FontSize.value(96.px))
        fontWeight(FontWeight.Bold)
        lineHeight(AppCSSVariables.wtH1LineHeight.value(112.px))
        fontFamily(rubikFontStr)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH1FontSize(42.px)
                AppCSSVariables.wtH1LineHeight(48.px)
            }
        }
    }

    val wtH1Light by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtH1FontSize.value(96.px))
        fontWeight(FontWeight.Light)
        lineHeight(AppCSSVariables.wtH1LineHeight.value(112.px))
        fontFamily(rubikFontStr)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH1FontSize(42.px)
                AppCSSVariables.wtH1LineHeight(48.px)
            }
        }
    }

    val wtH2 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtH2FontSize.value(60.px))
        fontWeight(FontWeight.Bold)
        lineHeight(AppCSSVariables.wtH2LineHeight.value(72.px))
        fontFamily(nunitoFontStr)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH2FontSize(24.px)
                AppCSSVariables.wtH2LineHeight(32.px)
            }
        }
    }

    val wtH3 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtH3FontSize.value(48.px))
        fontWeight(FontWeight.Bold)
        lineHeight(AppCSSVariables.wtH3LineHeight.value(56.px))
        fontFamily(nunitoFontStr)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH2FontSize(20.px)
                AppCSSVariables.wtH2LineHeight(28.px)
            }
        }
    }

    val wtH4 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtH4FontSize.value(34.px))
        fontWeight(FontWeight.Medium)
        lineHeight(AppCSSVariables.wtH4LineHeight.value(36.px))
        fontFamily(rubikFontStr)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH4FontSize(24.px)
                AppCSSVariables.wtH4LineHeight(28.px)
            }
        }
    }

    val wtH5 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtH5FontSize.value(24.px))
        fontWeight(FontWeight.Normal)
        lineHeight(AppCSSVariables.wtH5LineHeight.value(24.px))
        fontFamily(nunitoFontStr)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH5FontSize(20.px)
                AppCSSVariables.wtH5LineHeight(22.px)
            }
        }
    }

    val wtH6 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtH6FontSize.value(20.px))
        fontWeight(FontWeight.Normal)
        lineHeight(AppCSSVariables.wtH6LineHeight.value(22.px))
        fontFamily(nunitoFontStr)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH6FontSize(18.px)
                AppCSSVariables.wtH6LineHeight(20.px)
            }
        }
    }

    val wtBody by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtBodyFontSize.value(16.px))
        fontWeight(FontWeight.Normal)
        lineHeight(AppCSSVariables.wtBodyLineHeight.value(20.px))
        fontFamily(nunitoFontStr)
    }

    val wtButton by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtButtonFontSize.value(14.px))
        fontWeight(FontWeight.Bold)
        lineHeight(AppCSSVariables.wtButtonLineHeight.value(16.px))
        fontFamily(nunitoFontStr)
    }

    val wtCaption by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(AppCSSVariables.wtCaptionFontSize.value(10.px))
        fontWeight(FontWeight.Bold)
        lineHeight(AppCSSVariables.wtCaptionLineHeight.value(16.px))
        fontFamily(nunitoFontStr)
    }
}