package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.content.Footer
import com.sample.content.Hero
import com.sample.content.Links
import com.sample.markdown.MdLayout
import com.sample.style.AppCSSVariables
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.model.ThemeData
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text

@Composable
fun MdView(
    porfolioData: PortfolioData,
    mdData: List<MdNode>,
    isDarkTheme: Boolean,
    themeColorsName: String,
    themeData: ThemeData,
    onThemeBtnClick: (selectedThemeColors: String) -> Unit,
) {
    Hero(
        attrs = {
            classes(
                WtCols.wtCol12
            )
        },
        name = porfolioData.name,
        isDarkTheme = isDarkTheme
    )

    Div(
        attrs = {
            classes(
                WtRows.wtRow,
                WtOffsets.wtTopOffset96,
                WtOffsets.wtTopOffsetSm48,
            )
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
            }
        }
    ) {
        MdLayout(
            nodes = mdData,
        )
    }

    Footer(
        madeWith = porfolioData.madeWith,
        themeColorsName = themeColorsName,
        themeData = themeData,
        onThemeBtnClick = onThemeBtnClick,
    )
}