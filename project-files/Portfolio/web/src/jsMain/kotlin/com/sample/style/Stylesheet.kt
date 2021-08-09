package com.sample.style

import io.github.amanshuraikwar.portfolio.model.ThemeColorsData
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.selectors.CSSSelector

object AppCSSVariables : CSSVariables {
    val colorBackground by variable<Color>()
    val colorOnBackground by variable<Color>()
    val colorOnBackgroundDisabled by variable<Color>()
    val colorPrimary by variable<Color>()
    val colorOnPrimary by variable<Color>()

    val colorChipBg by variable<Color>()
    val colorHr by variable<Color>()

    val wtOffsetTopUnit by variable<CSSUnitValue>()
    val wtHorizontalLayoutGutter by variable<CSSUnitValue>()
    val wtFlowUnit by variable<CSSUnitValue>()

    val wtH1FontSize by variable<CSSUnitValue>()
    val wtH1LineHeight by variable<CSSUnitValue>()
    val wtH2FontSize by variable<CSSUnitValue>()
    val wtH2LineHeight by variable<CSSUnitValue>()
    val wtH3FontSize by variable<CSSUnitValue>()
    val wtH3LineHeight by variable<CSSUnitValue>()
    val wtH4FontSize by variable<CSSUnitValue>()
    val wtH4LineHeight by variable<CSSUnitValue>()
    val wtH5FontSize by variable<CSSUnitValue>()
    val wtH5LineHeight by variable<CSSUnitValue>()
    val wtH6FontSize by variable<CSSUnitValue>()
    val wtH6LineHeight by variable<CSSUnitValue>()
    val wtBodyFontSize by variable<CSSUnitValue>()
    val wtBodyLineHeight by variable<CSSUnitValue>()
    val wtButtonFontSize by variable<CSSUnitValue>()
    val wtButtonLineHeight by variable<CSSUnitValue>()
    val wtCaptionFontSize by variable<CSSUnitValue>()
    val wtCaptionLineHeight by variable<CSSUnitValue>()

    val wtColCount by variable<StylePropertyNumber>()
}

fun String.dropAlphaHex(): String {
    return "#${drop(3)}"
}

object AppStylesheet : StyleSheet() {

    fun updateColors(themeColorsData: ThemeColorsData) {
        CSSSelector.Universal style {
            console.log(themeColorsData.toString())
            AppCSSVariables.colorBackground(Color(themeColorsData.surfaceColor.dropAlphaHex()))
            AppCSSVariables.colorOnBackground(Color(themeColorsData.onSurfaceColor.dropAlphaHex()))
            AppCSSVariables.colorOnBackgroundDisabled(Color(themeColorsData.onSurfaceColor.dropAlphaHex()).copy(alpha = 0.34f))
            AppCSSVariables.colorPrimary(Color(themeColorsData.primaryColor.dropAlphaHex()))
            AppCSSVariables.colorOnPrimary(Color(themeColorsData.onPrimaryColor.dropAlphaHex()))
            AppCSSVariables.colorChipBg(Color(themeColorsData.onSurfaceColor.dropAlphaHex()).copy(alpha = 0.08f))
            AppCSSVariables.colorHr(Color(themeColorsData.onSurfaceColor.dropAlphaHex()).copy(alpha = 0.08f))
        }
    }

    init {
        CSSSelector.Universal style {
            AppCSSVariables.wtOffsetTopUnit(24.px)

            margin(0.px)
        }

        "body" style {
            backgroundColor(AppCSSVariables.colorBackground.value())
        }

        media(maxWidth(649.px)) {
            CSSSelector.Universal style {
                AppCSSVariables.wtOffsetTopUnit(16.px)
                AppCSSVariables.wtFlowUnit(16.px)
            }
        }

        CSSSelector.Attribute(
            name = "class",
            value = "wtCol",
            operator = CSSSelector.Attribute.Operator.Contains
        ) style {
            property("margin-right", AppCSSVariables.wtHorizontalLayoutGutter.value())
            property("margin-left", AppCSSVariables.wtHorizontalLayoutGutter.value())

            property(
                "flex-basis",
                "calc(8.33333%*${AppCSSVariables.wtColCount.value()} - ${AppCSSVariables.wtHorizontalLayoutGutter.value()}*2)"
            )
            property(
                "max-width",
                "calc(8.33333%*${AppCSSVariables.wtColCount.value()} - ${AppCSSVariables.wtHorizontalLayoutGutter.value()}*2)"
            )
            property("box-sizing", "border-box")
        }
    }
}
