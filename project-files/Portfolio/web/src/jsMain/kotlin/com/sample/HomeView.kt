package com.sample

import androidx.compose.runtime.Composable
import com.sample.content.BlogView
import com.sample.content.Footer
import com.sample.content.Hero
import com.sample.style.WtCols
import io.github.amanshuraikwar.portfolio.markdown.BlogListDataItem
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.model.ThemeData

@Composable
fun HomeView(
    porfolioData: PortfolioData,
    blogData: List<BlogListDataItem>,
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

    BlogView(blogData)

    Footer(
        madeWith = porfolioData.madeWith,
        themeColorsName = themeColorsName,
        themeData = themeData,
        onThemeBtnClick = onThemeBtnClick,
    )
}