package com.sample.components

import androidx.compose.runtime.Composable
import com.sample.style.AppCSSVariables
import com.sample.style.WtPadding
import com.sample.style.WtRadius
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.model.AppData
import io.github.amanshuraikwar.portfolio.model.AppLink
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

@Composable
fun MyApp(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    appData: AppData,
    isDarkTheme: Boolean,
) {
    Div(
        attrs = {
            attrs?.invoke(this)
            classes(
                WtTexts.wtLink,
            )
            style {
                display(DisplayStyle.Flex)
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    property("flex", 1)
                    alignItems(AlignItems.Start)
                }

                classes(
                    WtRadius.wtRadius16,
                    WtRadius.wtRadius8Sm,
                    WtPadding.wtPadding8,
                )
            }
        ) {
            Img(
                src = if (isDarkTheme) {
                    when (appData.id) {
                        "howmuch" -> "assets/howmuch_black_24.svg"
                        "splash" -> "assets/splash_black_24.svg"
                        "nextbus" -> "assets/nextbus_black_108.svg"
                        else -> "assets/link_black_24.svg"
                    }
                } else {
                    when (appData.id) {
                        "howmuch" -> "assets/howmuch_white_24.svg"
                        "splash" -> "assets/splash_white_24.svg"
                        "nextbus" -> "assets/nextbus_white_108.svg"
                        else -> "assets/link_white_24.svg"
                    }
                },
                attrs = {
                    style {
                        width(48.px)
                        height(48.px)
                        padding(8.px)
                        property(
                            "box-shadow",
                            "0.5px 0.5px 4px 2px rgba(0, 0, 0, 0.2)"
                        )
                        backgroundColor(AppCSSVariables.colorPrimary.value())
                    }
                    classes(
                        WtRadius.wtRadius12,
                        WtRadius.wtRadius8Sm,
                    )
                }
            )

            Div(
                attrs = {
                    style {
                        property("flex", 1)
                        marginLeft(16.px)
                        display(DisplayStyle.Flex)
                        flexDirection(FlexDirection.Column)

                    }
                }
            ) {
                Div(
                    attrs = {
                        classes(
                            WtTexts.wtH3
                        )
                    }
                ) {
                    Text(appData.title)
                }

                Div(
                    attrs = {
                        classes(
                            WtTexts.wtText1
                        )
                        style {
//                            marginTop(4.px)
                        }
                    }
                ) {
                    Text(appData.description)
                }

                Div(
                    attrs = {
                        classes(
                            WtTexts.wtText1
                        )
                        style {
                            marginTop(16.px)
                            display(DisplayStyle.Flex)
                            justifyContent(JustifyContent.FlexEnd)
                        }
                    }
                ) {
                    appData.appLinks.forEach { appLink ->
                        A(
                            attrs = {
                                classes(
                                    WtTexts.wtLink,
                                )
                                target(ATarget.Blank)
                            },
                            href = when (appLink) {
                                is AppLink.Github -> appLink.repoUrl
                                is AppLink.PlayStore -> appLink.playStoreListingUrl
                                is AppLink.Download -> appLink.downloadUrl
                                is AppLink.Other -> appLink.url
                            }
                        ) {
                            Img(
                                src = when (appLink) {
                                    is AppLink.Github -> {
                                        if (isDarkTheme) {
                                            "assets/github_white_24.svg"
                                        } else {
                                            "assets/github_black_24.svg"
                                        }
                                    }
                                    is AppLink.PlayStore -> {
                                        if (isDarkTheme) {
                                            "assets/google_play_white_24.svg"
                                        } else {
                                            "assets/google_play_black_24.svg"
                                        }
                                    }
                                    is AppLink.Download -> {
                                        if (isDarkTheme) {
                                            "assets/get_app_white_24.svg"
                                        } else {
                                            "assets/get_app_black_24.svg"
                                        }
                                    }
                                    is AppLink.Other -> {
                                        if (isDarkTheme) {
                                            "assets/link_white_24.svg"
                                        } else {
                                            "assets/link_black_24.svg"
                                        }
                                    }
                                },
                                attrs = {
                                    style {
                                        marginLeft(8.px)
                                        width(24.px)
                                        height(24.px)
                                        padding(8.px)
                                        backgroundColor(AppCSSVariables.colorAppLinkBtnBg.value())
                                    }
                                    classes(
                                        WtRadius.wtRadius12,
                                        WtRadius.wtRadius8Sm,
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}