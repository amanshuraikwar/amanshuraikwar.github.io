package com.sample

import androidx.compose.runtime.Composable
import com.sample.content.Experience
import com.sample.content.Hero
import com.sample.content.Links
import com.sample.content.Projects
import com.sample.style.*
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text

@Composable
fun Home(
    porfolioData: PortfolioData,
    onNextBusClick: () -> Unit,
    isDarkTheme: Boolean,
    onThemeBtnClick: (isDarkTheme: Boolean) -> Unit,
) {
    Div(
        attrs = {
            classes(WtContainer.wtContainerSm)
        }
    ) {
        Div(
            attrs = {
                classes(
                    WtRows.wtRow,
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48,
                )
            }
        ) {
            if (isDarkTheme) {
                Hero(
                    attrs = {
                        classes(
                            WtCols.wtCol12
                        )
                    },
                    name = porfolioData.name,
                    jobTitle = porfolioData.intro,
                    true,
                    onThemeBtnClick
                )
            } else {
                Hero(
                    attrs = {
                        classes(
                            WtCols.wtCol12
                        )
                    },
                    name = porfolioData.name,
                    jobTitle = porfolioData.intro,
                    false,
                    onThemeBtnClick
                )
            }
        }

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

        Experience(
            experience = porfolioData.experience,
            isDarkTheme = isDarkTheme,
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
            }
        )
    }
}