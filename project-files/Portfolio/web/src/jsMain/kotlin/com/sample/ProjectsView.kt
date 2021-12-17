package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.content.Hero
import com.sample.content.Links
import com.sample.content.Projects
import com.sample.markdown.MdLayout
import com.sample.style.AppCSSVariables
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.model.ThemeData
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text

@Composable
fun ProjectsView(
    porfolioData: PortfolioData,
    isDarkTheme: Boolean,
    themeColorsName: String,
    themeData: ThemeData,
    onThemeBtnClick: (selectedThemeColors: String) -> Unit,
) {
    if (isDarkTheme) {
        Hero(
            attrs = {
                classes(
                    WtCols.wtCol12
                )
            },
            name = porfolioData.name,
            isDarkTheme = true
        )
    } else {
        Hero(
            attrs = {
                classes(
                    WtCols.wtCol12
                )
            },
            name = porfolioData.name,
            isDarkTheme = false
        )
    }

    Div(
        attrs = {
            classes(
                WtOffsets.wtTopOffset96,
                WtOffsets.wtTopOffsetSm48,
            )
        }
    )

    MdLayout(
        nodes = listOf(
            MdNode.H1(
                "Projects"
            ),
        )
    )

    Div(
        attrs = {
            classes(WtContainer.wtContainerSm)
        }
    ) {
        Projects(
            isDarkTheme = isDarkTheme,
            apps = porfolioData.apps
        )

        Hr(
            attrs = {
                classes(
                    WtContent.sectionDividerHr,
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48,
                )
            }
        )

        Links(
            porfolioData.links,
            isDarkTheme = isDarkTheme
        )

        Hr(
            attrs = {
                classes(
                    WtContent.sectionDividerHr,
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48,
                )
            }
        )

        Div(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48,
                    WtTexts.wtH3
                )
                style {
                    color(AppCSSVariables.colorOnBackgroundDisabled.value())
                }
            }
        ) {
            Text(porfolioData.madeWith)
        }

        Div(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48,
                )
                style {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            ThemeSwitch(
                themeColorsName,
                themeData,
                onThemeChange = onThemeBtnClick
            )
        }

        Div(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48,
                )
            }
        )
    }
}