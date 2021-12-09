package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.content.Experience
import com.sample.content.Hero
import com.sample.content.Links
import com.sample.content.Projects
import com.sample.style.AppCSSVariables
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text

@Composable
fun Home(
    porfolioData: PortfolioData,
    isDarkTheme: Boolean,
    onThemeBtnClick: (isDarkTheme: Boolean) -> Unit,
) {
//    Div(
//        attrs = {
//            classes(WtContainer.wtContainerWide)
//            style {
//                backgroundColor(AppCSSVariables.colorHeroBg.value())
//            }
//        }
//    ) {
//        Div(
//            attrs = {
//                classes(
//                    WtRows.wtRow,
//                    WtOffsets.wtTopOffset48,
//                    WtOffsets.wtTopOffsetSm24,
//                )
//            }
//        ) {
            Hero(
                attrs = {
                    classes(
                        WtCols.wtCol12
                    )
                },
                name = porfolioData.name,
//                jobTitle = porfolioData.intro,
            )
//        }
//    }

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
                style {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.FlexEnd)
                }
            }
        ) {
            if (isDarkTheme) {
                ThemeSwitch(
                    true,
                    onThemeBtnClick
                )
            } else {
                ThemeSwitch(
                    false,
                    onThemeBtnClick
                )
            }
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