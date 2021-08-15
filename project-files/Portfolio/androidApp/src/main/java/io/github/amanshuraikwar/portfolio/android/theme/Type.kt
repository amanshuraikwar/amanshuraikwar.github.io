package io.github.amanshuraikwar.portfolio.android.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.amanshuraikwar.portfolio.R

private val RubikFontFamily = FontFamily(
    Font(R.font.rubik),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_medium, FontWeight.Medium)
)

val PortfolioTypography = Typography(
    defaultFontFamily = RubikFontFamily,
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
    ),
    overline = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp,
    )
)