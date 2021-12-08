package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.ThemeSwitch
import com.sample.content.Links
import com.sample.markdown.MdLayout
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import com.sample.style.AppCSSVariables
import com.sample.style.WtContainer
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
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
fun Home(
    porfolioData: PortfolioData,
    blogData: List<MdNode>,
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
            MdLayout(
                nodes = blogData,
//                nodes = listOf(
//                    MdNode.H1("A two line blog title"),
//                    MdNode.Date("DECEMBER 7, 2021"),
//                    MdNode.Spacer,
//
//                    MdNode.P("You’re an iOS developer. Maybe you’ve been doing it for a while, or maybe you’re new to the scene. Maybe you prefer to think of yourself as an “iOS engineer,” because it sounds better and no one seems to agree on whether there’s a difference. Whatever the situation: you’re feeling restless. You’ve worked on interesting projects before — successful ones, even — and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you can’t shake the feeling that none of the work you’ve done is truly yours. Much of your time has been spent helping others achieve their goals — and that’s okay! — but you increasingly feel like you’ve got some of your own goals to chase too."),
//
//                    MdNode.Img(
//                        url = "https://images.unsplash.com/photo-1637573544833-85aeba5247eb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3540&q=80",
//                        label = "An Image"
//                    ),
//                    MdNode.H3("This is a second level heading or a section-heading"),
//                    MdNode.P("Keep in mind that an interactive transition can look like anything, really, and these tips aren’t universally applicable to any and all interactive transitions; rather, for the sake of example, these are focused primarily on “swipe down to dismiss” interactions, and the code samples are derived from my implementation of the “Tags” view being dismissed in the video above. My hope is for these to get you thinking about how you might add similar polish to your own transitions, even if they operate slightly differently than mine."),
//                    MdNode.P("Making interactions feel “natural” is a bit of a subjective thing, but for me, there were a few details I knew I wanted to get right when it came to dismissing my view controllers interactively. They’re all fairly obvious best practices when it comes to animating and interacting with views, but some of them can be a bit tricky to get right in the context of view controller transitions."),
//                    MdNode.Btn(
//                        text = "Get it on Play Store",
//                        url = "https://google.com"
//                    ),
//                )
            )
        }
        /*
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
         */

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