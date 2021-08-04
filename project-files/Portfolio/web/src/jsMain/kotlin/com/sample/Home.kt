package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.HrefButton
import com.sample.components.MyApp
import com.sample.components.ThemeSwitch
import com.sample.content.MyIntro
import com.sample.content.MyName
import com.sample.style.*
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
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
        MyName(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48
                )
            },
            name = porfolioData.name
        )

        MyIntro(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset24,
                    WtOffsets.wtTopOffsetSm16
                )
            },
            porfolioData.intro
        )

        H2(
            attrs = {
                classes(
                    WtTexts.wtH2,
                    WtOffsets.wtTopOffset48,
                    WtOffsets.wtTopOffsetSm48
                )
            }
        ) {
            Text("My Links")
        }

        Div(
            attrs = {
                classes(
                    WtRows.wtRow,
                    WtOffsets.wtTopOffset24,
                    WtOffsets.wtTopOffsetSm16
                )
            }
        ) {

            if (isDarkTheme) {

                porfolioData.links.forEach {
                    HrefButton(
                        attrs = {
                            classes(WtCols.wtCol6, WtCols.wtColSm12)
                            style {
                                padding(4.px)
                            }
                        },
                        iconUrl = getLinkIconUrl(it.id, true),
                        text = it.title,
                        link = it.url,
                    )
                }
            } else {
                porfolioData.links.forEach {
                    HrefButton(
                        attrs = {
                            classes(WtCols.wtCol6, WtCols.wtColSm12)
                            style {
                                padding(4.px)
                            }
                        },
                        iconUrl = getLinkIconUrl(it.id, false),
                        text = it.title,
                        link = it.url,
                    )
                }
            }
        }

        H2(
            attrs = {
                classes(
                    WtTexts.wtH2,
                    WtOffsets.wtTopOffset48,
                    WtOffsets.wtTopOffsetSm48
                )
            }
        ) {
            Text("My Apps")
        }

        Div(
            attrs = {
                classes(
                    WtRows.wtRow,
                    WtOffsets.wtTopOffset24,
                    WtOffsets.wtTopOffsetSm16
                )
            }
        ) {

            if (isDarkTheme) {
                porfolioData.apps.forEach {
                    MyApp(
                        attrs = {
                            classes(WtCols.wtCol12, WtCols.wtColSm12)
                            style {
                                padding(4.px)
                            }
                        },
                        appData = it,
                        isDarkTheme = true
                    )
                }
            } else {
                porfolioData.apps.forEach {
                    MyApp(
                        attrs = {
                            classes(WtCols.wtCol12, WtCols.wtColSm12)
                            style {
                                padding(4.px)
                            }
                        },
                        appData = it,
                        isDarkTheme = false
                    )
                }
            }
        }

        Div(
            attrs = {
                classes(
                    WtRows.wtRow,
                    WtOffsets.wtTopOffset48,
                    WtOffsets.wtTopOffsetSm48
                )
            }
        ) {
            if (isDarkTheme) {
                ThemeSwitch(
                    attrs = {
                        classes(WtCols.wtCol6, WtCols.wtColSm12)
                        style {
                            padding(4.px)
                        }
                    },
                    isDarkTheme = true,
                    onThemeChange = onThemeBtnClick
                )
            } else {
                ThemeSwitch(
                    attrs = {
                        classes(WtCols.wtCol6, WtCols.wtColSm12)
                        style {
                            padding(4.px)
                        }
                    },
                    isDarkTheme = false,
                    onThemeChange = onThemeBtnClick
                )
            }
        }

        MyIntro(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset96,
                    WtOffsets.wtTopOffsetSm48
                )
            },
            porfolioData.madeWith
        )

        Div(
            attrs = {
                classes(
                    WtOffsets.wtTopOffset48,
                    WtOffsets.wtTopOffsetSm24
                )
            }
        )
    }
}

fun getLinkIconUrl(id: String, isDarkTheme: Boolean): String {
    return when (id) {
        "resume" -> if (isDarkTheme) {
            "assets/article_black_24.svg"
        } else {
            "assets/article_white_24.svg"
        }
        "playstore" -> if (isDarkTheme) {
            "assets/google_play_black_24.svg"
        } else {
            "assets/google_play_white_24.svg"
        }
        "unsplash" -> if (isDarkTheme) {
            "assets/camera_alt_black_24.svg"
        } else {
            "assets/camera_alt_white_24.svg"
        }
        "github" -> if (isDarkTheme) {
            "assets/github_black_24.svg"
        } else {
            "assets/github_white_24.svg"
        }
        "linkedin" -> if (isDarkTheme) {
            "assets/linkedin_black_24.svg"
        } else {
            "assets/linkedin_white_24.svg"
        }
        "medium" -> if (isDarkTheme) {
            "assets/medium_black_24.svg"
        } else {
            "assets/medium_white_24.svg"
        }
        "twitter" -> if (isDarkTheme) {
            "assets/twitter_black_24.svg"
        } else {
            "assets/twitter_white_24.svg"
        }
        "instagram" -> if (isDarkTheme) {
            "assets/instagram_black_24.svg"
        } else {
            "assets/instagram_white_24.svg"
        }
        else -> if (isDarkTheme) {
            "assets/link_black_24.svg"
        } else {
            "assets/link_white_24.svg"
        }
    }
}