package io.github.amanshuraikwar.portfolio.android.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import io.github.amanshuraikwar.portfolio.model.HomePageData
import io.github.amanshuraikwar.portfolio.android.home.view.*

@Composable
fun HomeScreen(
    screenState: HomeScreenState,
    onLinkClick: (url: String) -> Unit,
) {
    when(screenState) {
        HomeScreenState.Fetching -> {
            FetchingView()
        }
        is HomeScreenState.Success -> {
            LazyColumn(
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.systemBars,
                    applyTop = true,
                    applyBottom = true,
                )
            ) {
                items(
                    items = screenState.homePageDataList,
                ) { item ->
                    when (item) {
                        is HomePageData.Heading -> {
                            HeadingView(
                                name = item.name,
                                intro = item.intro
                            )
                        }
                        is HomePageData.LastUpdated -> {
                            LastUpdatedView(message = item.message)
                        }
                        is HomePageData.MadeWith -> {
                            MadeWithView(message = item.message)
                        }
                        is HomePageData.MyLinks -> {
                            MyLinksView(
                                heading = item.heading,
                                links = item.linkDataList,
                                onLinkClick = onLinkClick
                            )
                        }
                    }
                }
            }
        }
    }
}