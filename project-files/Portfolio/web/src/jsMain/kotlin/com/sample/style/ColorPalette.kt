package com.sample.style

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color

fun CSSColorValue.copy(alpha: Float): CSSColorValue {
    val colorStr = this.toString()
    console.log("yoyo", colorStr, colorStr.matches(Regex("#[0-9A-Fa-f]{6}")))
    return when {
        this is Color.RGB -> {
            Color.RGBA(
                r,
                g,
                b,
                alpha
            )
        }
        this is Color.RGBA -> {
            Color.RGBA(
                r,
                g,
                b,
                alpha
            )
        }
        this is Color.HSL -> {
            Color.HSLA(
                h,
                s,
                l,
                alpha
            )
        }
        this is Color.HSLA -> {
            Color.HSLA(
                h,
                s,
                l,
                alpha
            )
        }
        colorStr.matches(Regex("#[0-9A-Fa-z]{6}")) -> {
            colorStr.drop(1).let {
                Color.RGBA(
                    "${it[0]}${it[1]}".toLong(radix = 16),
                    "${it[2]}${it[3]}".toLong(radix = 16),
                    "${it[4]}${it[5]}".toLong(radix = 16),
                    alpha
                )
            }
        }
        else -> {
            this
        }
    }
}