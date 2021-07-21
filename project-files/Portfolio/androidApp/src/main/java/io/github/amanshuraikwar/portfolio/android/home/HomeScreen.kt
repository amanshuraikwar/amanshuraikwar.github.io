package io.github.amanshuraikwar.portfolio.android.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import io.github.amanshuraikwar.portfolio.android.home.view.FetchingView
import io.github.amanshuraikwar.portfolio.android.home.view.HeadingView
import io.github.amanshuraikwar.portfolio.android.home.view.MyLinksView

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
                item {
                    HeadingView(
                        name = screenState.portfolioData.name,
                        intro = screenState.portfolioData.intro,
                    )
                }

                item {
                    MyLinksView(
                        heading = "You can find me at...",
                        links = screenState.portfolioData.links,
                        onLinkClick = onLinkClick
                    )
                }
            }
        }
    }
}