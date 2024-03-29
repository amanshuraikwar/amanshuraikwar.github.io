package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.ButtonSize
import com.sample.components.ButtonStyle
import com.sample.components.ImgHrefButton
import com.sample.style.WtCols
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.model.LinkData
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
fun Links(
    links: List<LinkData>,
    isDarkTheme: Boolean
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
        Div(
            attrs = {
                classes(
                    WtCols.wtCol12,
                )
                style {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    alignItems(AlignItems.Center)
                    flexWrap(FlexWrap.Wrap)
                }
            }
        ) {
            if (isDarkTheme) {
                links.forEachIndexed { _, linkData ->
                    ImgHrefButton(
                        attrs = {
                            style {
                                margin(8.px)
                            }
                        },
                        src = getLinkIconUrl(linkData.id, true),
                        href = linkData.url,
                        buttonStyle = ButtonStyle.SOLID,
                        buttonSize = ButtonSize.NORMAL
                    )
                }
            } else {
                links.forEachIndexed { index, linkData ->
                    ImgHrefButton(
                        attrs = {
                            style {
                                margin(8.px)
                            }
                        },
                        src = getLinkIconUrl(linkData.id, false),
                        href = linkData.url,
                        buttonStyle = ButtonStyle.SOLID,
                        buttonSize = ButtonSize.NORMAL
                    )
                }
            }
        }
    }
}

fun getLinkIconUrl(id: String, isDarkTheme: Boolean): String {
    return when (id) {
        "resume" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/article_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/article_white_24.svg"
        }
        "playstore" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/google_play_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/google_play_white_24.svg"
        }
        "unsplash" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/unsplash_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/unsplash_white_24.svg"
        }
        "github" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/github_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/github_white_24.svg"
        }
        "linkedin" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/linkedin_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/linkedin_white_24.svg"
        }
        "medium" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/medium_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/medium_white_24.svg"
        }
        "twitter" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/twitter_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/twitter_white_24.svg"
        }
        "instagram" -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/instagram_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/instagram_white_24.svg"
        }
        else -> if (isDarkTheme) {
            "https://amanshuraikwar.github.io/assets/link_black_24.svg"
        } else {
            "https://amanshuraikwar.github.io/assets/link_white_24.svg"
        }
    }
}