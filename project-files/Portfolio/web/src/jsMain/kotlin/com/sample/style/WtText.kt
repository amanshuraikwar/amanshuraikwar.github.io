package com.sample.style

import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.selectors.CSSSelector
import org.jetbrains.compose.web.css.selectors.hover
import org.jetbrains.compose.web.css.selectors.plus

object WtTexts : StyleSheet(AppStylesheet) {
    const val libreBaskervillaFontStr = "Libre Baskerville,Rubik,system-ui,-apple-system,Arial,serif,sans-serif"
    const val latoFontStr = "lato,Rubik,system-ui,-apple-system,Arial,serif,sans-serif"

    val wtHero by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(60.px)
        property("font-size", AppCSSVariables.wtHeroFontSize.value(60.px))
        property("font-weight", 700)
        property("line-height", 64.px)
        property("line-height", AppCSSVariables.wtHeroLineHeight.value(64.px))

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtHeroFontSize(42.px)
                AppCSSVariables.wtHeroLineHeight(48.px)
            }
        }

        property(
            "font-family",
            libreBaskervillaFontStr
        )
    }

    val wtSubtitle2 by style {
        color("#ffffff")
        fontSize(28.px)
        property("font-size", AppCSSVariables.wtSubtitle2FontSize.value(28.px))
        property("letter-spacing", "normal")
        property("font-weight", 300)
        property("line-height", 40.px)
        property("line-height", AppCSSVariables.wtSubtitle2LineHeight.value(40.px))

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtSubtitle2FontSize(24.px)
                AppCSSVariables.wtSubtitle2LineHeight(32.px)
            }
        }

        property(
            "font-family",
            "Rubik,system-ui,-apple-systemArial,sans-serif"
        )
    }

    val wtText1 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(18.px)
        property("letter-spacing", "normal")
        property("font-weight", 400)
        property("line-height", 28.px)

        property(
            "font-family",
            latoFontStr
        )
    }

    val wtText1ThemeDark by style {
        color(Color.RGBA(255, 255, 255, 0.6))
    }

    val wtText2 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(15.px)
        property("letter-spacing", "normal")
        property("font-weight", 400)
        property("line-height", 24.px)

        property(
            "font-family",
            latoFontStr
        )
    }

    val wtText3 by style {
        color(Color.RGBA(39, 40, 44, .7))
        fontSize(12.px)
        property("letter-spacing", "normal")
        property("font-weight", 400)
        property("line-height", 16.px)

        property(
            "font-family",
            "system-ui,-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Droid Sans,Helvetica Neue,Arial,sans-serif"
        )
    }

    val wtTextPale by style {
        color(Color.RGBA(255, 255, 255, 0.30))
    }

    val wtText2ThemeDark by style {
        color(Color.RGBA(255, 255, 255, 0.6))
    }

    val wtText3ThemeDark by style {
        color(Color.RGBA(255, 255, 255, 0.6))
    }


    val wtH2 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(31.px)
        property("font-size", AppCSSVariables.wtH2FontSize.value(31.px))
        property("font-weight", 700)
        property("line-height", 40.px)

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH2FontSize(24.px)
                AppCSSVariables.wtH2LineHeight(32.px)
            }
        }

        property(
            "font-family",
            libreBaskervillaFontStr
        )
    }

    val wtH2ThemeDark by style {
        color("#fff")
    }

    val wtH3 by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(21.px)
        property("font-size", AppCSSVariables.wtH3FontSize.value(24.px))
        property("letter-spacing", "normal")
        property("font-weight", 700)
        property("line-height", 28.px)
        property("line-height", AppCSSVariables.wtH3LineHeight.value(32.px))

        property(
            "font-family",
            latoFontStr
        )

        media(maxWidth(750.px)) {
            self style {
                AppCSSVariables.wtH2FontSize(20.px)
                AppCSSVariables.wtH2LineHeight(28.px)
            }
        }
    }

    val wtH3ThemeDark by style {
        color("#fff")
    }

    val wtButton by style {
        color(AppCSSVariables.colorOnBackground.value())
        backgroundColor(AppCSSVariables.colorCardBg.value())
        fontSize(18.px)
        display(DisplayStyle.InlineBlock)
        property("text-decoration", "none")
        property("line-height", 24.px)
        property("font-weight", 400)
        property("width", "fit-content")
        property("transition", "0.3s")
        property(
            "font-family",
            latoFontStr
        )

        hover(self) style {
            backgroundColor(AppCSSVariables.colorOnBackground.value())
            color(AppCSSVariables.colorBackground.value())
        }

        active(self) style {
            backgroundColor(AppCSSVariables.colorPrimary.value())
            color(AppCSSVariables.colorOnPrimary.value())
        }
    }

    val wtLink by style {
        color(AppCSSVariables.colorOnBackground.value())
        fontSize(18.px)
        display(DisplayStyle.InlineBlock)
        property("text-decoration", "none")
        property("line-height", 24.px)
        property("font-weight", 400)
        property("width", "fit-content")

        property(
            "font-family",
            latoFontStr
        )

        hover(self) style {
        }

        active(self) style {
        }
    }

    fun active() = CSSSelector.PseudoClass.active
    fun active(selector: CSSSelector) = selector + active()

    val wtLangButton by style {
        display(DisplayStyle.LegacyInlineFlex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        backgroundColor(Color("transparent"))
        border(0.px)

        property("outline", "none")

        hover(self) style {
            backgroundColor(Color.RGBA(255, 255, 255, 0.1))
        }
    }

    val wtButtonContrast by style {
        color("white")
        backgroundColor("#27282c")

        hover(self) style {
            backgroundColor(Color.RGBA(39, 40, 44, .7))
        }
    }

    val wtSocialButtonItem by style {
        property("margin-right", 16.px)
        marginLeft(16.px)
        padding(12.px)
        backgroundColor("transparent")
        display(DisplayStyle.LegacyInlineFlex)

        hover(self) style {
            backgroundColor(Color.RGBA(255, 255, 255, 0.1))
            property("border-radius", "24px")
        }

        media(maxWidth(750.px)) {
            self style {
                property("margin-right", 8.px)
                property("margin-left", 8.px)
            }
        }
    }
}