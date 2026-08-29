package com.sample

import androidx.compose.runtime.Composable
import com.sample.content.Footer
import com.sample.content.Hero
import com.sample.content.RssFeedBrowser
import com.sample.markdown.MdLayout
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import io.github.amanshuraikwar.portfolio.model.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.theme.model.ThemeData
import org.jetbrains.compose.web.dom.Div

@Composable
fun RssFeedsView(
    portfolioData: PortfolioData,
    isDarkTheme: Boolean,
    themeColorsName: String,
    themeData: ThemeData,
    onThemeBtnClick: (selectedThemeColors: String) -> Unit,
) {
    Hero(
        attrs = { classes(WtCols.wtCol12) },
        name = portfolioData.name,
        isDarkTheme = isDarkTheme,
    )

    Div(
        attrs = {
            classes(WtOffsets.wtTopOffset96, WtOffsets.wtTopOffsetSm48)
        }
    )

    MdLayout(nodes = listOf(MdNode.H1("RSS feeds")))

    Div(
        attrs = {
            classes(WtContainer.wtContainerWide, WtOffsets.wtTopOffset48, WtOffsets.wtTopOffsetSm24)
        }
    ) {
        RssFeedBrowser()
    }

    Footer(
        madeWith = portfolioData.madeWith,
        themeColorsName = themeColorsName,
        themeData = themeData,
        onThemeBtnClick = onThemeBtnClick,
    )
}
