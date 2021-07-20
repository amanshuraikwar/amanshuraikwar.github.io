package io.github.amanshuraikwar.portfolio.android.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import io.github.amanshuraikwar.portfolio.android.home.view.*
import io.github.amanshuraikwar.portfolio.model.HomePageData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    screenState: HomeScreenState,
    onLinkClick: (url: String) -> Unit,
) {
    when (screenState) {
        HomeScreenState.Fetching -> {
            FetchingView(modifier)
        }
        is HomeScreenState.Success -> {
            LazyColumn(
                modifier,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.systemBars,
                    applyTop = true,
                    applyBottom = false,
                    additionalTop = 48.dp,
                    additionalBottom = 48.dp
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