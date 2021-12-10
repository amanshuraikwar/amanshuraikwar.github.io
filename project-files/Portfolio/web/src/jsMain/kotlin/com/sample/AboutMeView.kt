package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.content.Hero
import com.sample.content.Links
import com.sample.markdown.MdLayout
import com.sample.style.AppCSSVariables
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text

@Composable
fun AboutMeView(
    porfolioData: PortfolioData,
    isDarkTheme: Boolean,
    onThemeBtnClick: (isDarkTheme: Boolean) -> Unit,
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
            classes(WtContainer.wtContainerSm)
        }
    ) {
        MdLayout(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48,
                )
            },
            nodes = listOf(
                MdNode.H1(
                    "About Me :)"
                ),
                MdNode.Date(
                    "Updated December 10, 2021"
                ),
                MdNode.Spacer,
                MdNode.P(
                    "Your neighbourhood Software Engineer who likes to builds apps for himself and hopefully others. I will not fix your printer for you unless you buy me food :)"
                ),
                MdNode.P(
                    "I also click photos sometimes."
                ),
                MdNode.P(
                    "I am trying to write more, however slowly ;)"
                ),
                MdNode.Img(
                    label = "Me",
                    url = "../../assets/me.jpeg"
                ),
                porfolioData.links
                    .find {
                        it.id == "twitter"
                    }
                    .let { twitterLinkData ->
                        MdNode.Btn(
                            text = if (twitterLinkData != null) {
                                "Follow me on Twitter"
                            } else {
                                "Go to Home Page"
                            },
                            url = twitterLinkData?.url ?: "https://amanshuraikwar.github.io"
                        )
                    }
            )
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