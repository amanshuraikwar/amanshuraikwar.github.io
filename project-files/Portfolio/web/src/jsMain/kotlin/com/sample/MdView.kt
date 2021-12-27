package com.sample

import androidx.compose.runtime.Composable
import com.sample.content.Footer
import com.sample.content.Hero
import com.sample.markdown.MdLayout
import com.sample.style.WtCols
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.model.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.theme.model.ThemeData
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.dom.Div

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