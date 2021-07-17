package com.sample

import androidx.compose.runtime.Composable
import com.sample.content.*
import io.github.amanshuraikwar.portfolio.model.HomePageData

@Composable
fun Home(
    homePageDataList: List<HomePageData>,
    onNextBusClick: () -> Unit,
) {
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

//                MyApps {
//                    if (it == "nextbus") {
//                        onNextBusClick()
//                    }
//                }
            }
            is HomePageData.LastUpdated -> {
                LastUpdated(homePageData.message)
            }
            is HomePageData.MadeWith -> {
                MadeWith(homePageData.message)
            }
        }
    }
}