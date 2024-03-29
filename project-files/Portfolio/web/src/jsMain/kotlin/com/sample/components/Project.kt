package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.WtContent
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.model.AppData
import io.github.amanshuraikwar.portfolio.model.AppLink
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun Project(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    appData: AppData,
    isDarkTheme: Boolean,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Stretch)
            }
        }
    ) {
        Img(
            src = appData.artUrl,
            attrs = {
                classes(WtContent.projectArt)
                style {
                    borderRadius(16.px)
                    // to fix the weird image stretch safari issue
                    // https://github.com/neptunian/react-photo-gallery/issues/174
                    width(100.percent)
                }
            }
        )

        Div(
            attrs = {
                classes(
                    WtTexts.wtH4
                )
                style {
                    marginTop(24.px)
                }
            }
        ) {
            Text(appData.title)
        }

        Div(
            attrs = {
                classes(
                    WtTexts.wtBody
                )
                style {
                    marginTop(16.px)
                }
            }
        ) {
            Text(appData.description)
        }

        Div(
            attrs = {
                style {
                    marginTop(16.px)
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.Start)
                }
            }
        ) {
            Chip(
                src = if (appData.maintained) {
                    if (isDarkTheme) {
                        "https://amanshuraikwar.github.io/assets/code_white_24.svg"
                    } else {
                        "https://amanshuraikwar.github.io/assets/code_black_24.svg"
                    }
                } else {
                    if (isDarkTheme) {
                        "https://amanshuraikwar.github.io/assets/code_off_white_24.svg"
                    } else {
                        "https://amanshuraikwar.github.io/assets/code_off_black_24.svg"
                    }
                },
                text = if (appData.maintained) {
                    "ACTIVELY MAINTAINED"
                } else {
                    "NO LONGER MAINTAINED"
                }
            )
        }

        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.FlexEnd)
                    style {
                        marginTop(16.px)
                    }
                }
            }
        ) {
            appData.appLinks.forEach { appLink ->
                ImgHrefButton(
                    href = when (appLink) {
                        is AppLink.Github -> appLink.repoUrl
                        is AppLink.PlayStore -> appLink.playStoreListingUrl
                        is AppLink.Download -> appLink.downloadUrl
                        is AppLink.Other -> appLink.url
                    },
                    src = when (appLink) {
                        is AppLink.Github -> {
                            if (isDarkTheme) {
                                "https://amanshuraikwar.github.io/assets/github_white_24.svg"
                            } else {
                                "https://amanshuraikwar.github.io/assets/github_black_24.svg"
                            }
                        }
                        is AppLink.PlayStore -> {
                            if (isDarkTheme) {
                                "https://amanshuraikwar.github.io/assets/google_play_white_24.svg"
                            } else {
                                "https://amanshuraikwar.github.io/assets/google_play_black_24.svg"
                            }
                        }
                        is AppLink.Download -> {
                            if (isDarkTheme) {
                                "https://amanshuraikwar.github.io/assets/get_app_white_24.svg"
                            } else {
                                "https://amanshuraikwar.github.io/assets/get_app_black_24.svg"
                            }
                        }
                        is AppLink.Other -> {
                            if (isDarkTheme) {
                                "https://amanshuraikwar.github.io/assets/link_white_24.svg"
                            } else {
                                "https://amanshuraikwar.github.io/assets/link_black_24.svg"
                            }
                        }
                    },
                    attrs = {
                        style {
                            marginLeft(8.px)
                        }
                    },
                    buttonStyle = ButtonStyle.OUTLINE,
                    buttonSize = ButtonSize.SMALL
                )
            }
        }
    }
}