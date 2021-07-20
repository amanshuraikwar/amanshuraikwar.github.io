package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.Button
import com.sample.content.*
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.model.HomePageData
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun Home(
    homePageDataList: List<HomePageData>,
    onNextBusClick: () -> Unit,
    isDarkTheme: Boolean,
    onThemeBtnClick: () -> Unit,
) {
    Section {
        Div(
            attrs = {
                classes(WtContainer.wtContainer)
            }
        ) {
            Div(
                attrs = {
                    classes(
                        WtRows.wtRow,
                        WtRows.wtRowSizeM,
                        WtOffsets.wtTopOffset48,
                        WtOffsets.wtTopOffsetSm24
                    )
                }
            ) {
                Div(
                    attrs = {
                        classes(WtCols.wtCol10)
                    }
                ) {}

                Div(
                    attrs = {
                        classes(WtCols.wtCol2)
                    }
                ) {
                    Button(
                        onClick = onThemeBtnClick
                    ) {
                        Span(
                            attrs = {
                                classes(
                                    "material-icons",
                                )
                            }
                        ) {
                            if (isDarkTheme) {
                                Text("dark_mode")
                            } else {
                                Text("light_mode")
                            }
                        }
                    }
                }
            }
        }
    }

    for (homePageData in homePageDataList) {
        when (homePageData) {
            is HomePageData.Heading -> {
                Header(
                    name = homePageData.name,
                    intro = homePageData.intro,
                )
            }
            is HomePageData.MyLinks -> {
                MyLinks(
                    heading = homePageData.heading,
                    links = homePageData.linkDataList
                )

                MyApps {
                    if (it == "nextbus") {
                        onNextBusClick()
                    }
                }
            }
            is HomePageData.LastUpdated -> {
                LastUpdated(homePageData.message)
            }
            is HomePageData.MadeWith -> {
                MadeWith(homePageData.message)
            }
        }
    }

    Div(
        attrs = {
            classes(
                WtRows.wtRow,
                WtRows.wtRowSizeM,
                WtOffsets.wtTopOffset48,
            )
        }
    ) {}
}