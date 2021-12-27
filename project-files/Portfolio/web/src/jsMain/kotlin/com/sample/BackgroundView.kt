package com.sample

import androidx.compose.runtime.Composable
import com.sample.content.Experience
import com.sample.content.Footer
import com.sample.content.Hero
import com.sample.markdown.MdLayout
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import io.github.amanshuraikwar.portfolio.model.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.theme.model.ThemeData
import org.jetbrains.compose.web.dom.Div

@Composable
fun BackgroundView(
    porfolioData: PortfolioData,
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
                WtContainer.wtContainerSm,
                WtOffsets.wtTopOffset96,
                WtOffsets.wtTopOffsetSm48,
            )
        }
    )

    MdLayout(
        nodes = listOf(
            MdNode.H1(
                "My Background"
            ),
        )
    )

    Div(
        attrs = {
            classes(WtContainer.wtContainerSm)
        }
    ) {
        Experience(
            experience = porfolioData.experience,
            isDarkTheme = isDarkTheme,
        )
    }

    MdLayout(
        nodes = listOf(
            porfolioData.links
                .find {
                    it.id == "linkedin"
                }
                .let { linkData ->
                    MdNode.Btn(
                        text = if (linkData != null) {
                            "Go to my Linkedin"
                        } else {
                            "Go to Home Page"
                        },
                        url = linkData?.url ?: "https://amanshuraikwar.github.io"
                    )
                }
        )
    )

    Footer(
        madeWith = porfolioData.madeWith,
        themeColorsName = themeColorsName,
        themeData = themeData,
        onThemeBtnClick = onThemeBtnClick,
    )
}