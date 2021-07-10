package com.sample.style

import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.selectors.CSSSelector

object AppCSSVariables : CSSVariables {
    val colorBackground by variable<Color>()
    val colorOnBackground by variable<Color>()
    val colorPrimary by variable<Color>()
    val colorOnPrimary by variable<Color>()

    val wtOffsetTopUnit by variable<CSSUnitValue>()
    val wtHorizontalLayoutGutter by variable<CSSUnitValue>()
    val wtFlowUnit by variable<CSSUnitValue>()

    val wtHeroFontSize by variable<CSSUnitValue>()
    val wtHeroLineHeight by variable<CSSUnitValue>()
    val wtSubtitle2FontSize by variable<CSSUnitValue>()
    val wtSubtitle2LineHeight by variable<CSSUnitValue>()
    val wtH2FontSize by variable<CSSUnitValue>()
    val wtH2LineHeight by variable<CSSUnitValue>()
    val wtH3FontSize by variable<CSSUnitValue>()
    val wtH3LineHeight by variable<CSSUnitValue>()

    val wtColCount by variable<StylePropertyNumber>()
}


object AppStylesheet : StyleSheet() {

    init {
        data class ColorPalette(
            val colorBackground: Color,
            val colorOnBackground: Color,
            val colorPrimary: Color,
            val colorOnPrimary: Color,
        )

        val colorPaletteList = listOf(
            ColorPalette(
                colorBackground = Color("#212121"),
                colorOnBackground = Color("#ffffff"),
                colorPrimary = Color("#FFCDD2"),
                colorOnPrimary = Color("#4E342E"),
            ),
            ColorPalette(
                colorBackground = Color("#FFF2CA"),
                colorOnBackground = Color("#52403A"),
                colorPrimary = Color("#F57C00"),
                colorOnPrimary = Color("#4E342E"),
            )
        )

        CSSSelector.Universal style {
            val colorPalette = colorPaletteList.random()
            AppCSSVariables.colorBackground(colorPalette.colorBackground)
            AppCSSVariables.colorOnBackground(colorPalette.colorOnBackground)
            AppCSSVariables.colorPrimary(colorPalette.colorPrimary)
            AppCSSVariables.colorOnPrimary(colorPalette.colorOnPrimary)
            AppCSSVariables.wtOffsetTopUnit(24.px)

            margin(0.px)
        }

        "body" style  {
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
