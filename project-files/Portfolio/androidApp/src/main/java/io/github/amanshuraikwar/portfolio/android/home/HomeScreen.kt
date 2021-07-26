package io.github.amanshuraikwar.portfolio.android.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import io.github.amanshuraikwar.portfolio.android.home.view.FetchingView
import io.github.amanshuraikwar.portfolio.android.home.view.MyAppsView
import io.github.amanshuraikwar.portfolio.android.home.view.MyLinksView
import io.github.amanshuraikwar.portfolio.android.ui.CollapsingHeaderLayout
import io.github.amanshuraikwar.portfolio.android.ui.MyNameCollapsingHeader
import io.github.amanshuraikwar.portfolio.android.ui.rememberCollapsingHeaderState

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    screenState: HomeScreenState,
    isDarkTheme: Boolean,
    onThemeSwitchValueChange: (isDarkThemeEnabled: Boolean) -> Unit,
    onLinkClick: (url: String) -> Unit,
) {
    when (screenState) {
        HomeScreenState.Fetching -> {
            FetchingView(modifier)
        }
        is HomeScreenState.Success -> {
            val collapsingHeaderLayoutState = rememberCollapsingHeaderState()

            CollapsingHeaderLayout(
                modifier = modifier,
                collapsingHeaderLayoutState = collapsingHeaderLayoutState,
                header = {
                    Surface(
                        color = MaterialTheme.colors.surface,
                        elevation = 8.dp
                    ) {
                        MyNameCollapsingHeader(
                            modifier = Modifier
                                .statusBarsPadding(),
                            name = screenState.portfolioData.name,
                            intro = screenState.portfolioData.intro,
                            expandFraction = collapsingHeaderLayoutState.expandFraction.value,
                            headerHeight = collapsingHeaderLayoutState.headerHeight,
                            collapsedHeight = collapsingHeaderLayoutState.collapsedHeight,
                            expandedHeight = collapsingHeaderLayoutState.expandedHeight,
                            isDarkTheme = isDarkTheme,
                            onThemeSwitchValueChange = onThemeSwitchValueChange
                        )
                    }
                }
            ) {
                LazyColumn(
                    modifier,
                    contentPadding = rememberInsetsPaddingValues(
                        insets = LocalWindowInsets.current.systemBars,
                        additionalBottom = 128.dp
                    )
                ) {
                    item {
                        MyLinksView(
                            heading = "You can find me at...",
                            links = screenState.portfolioData.links,
                            onLinkClick = onLinkClick
                        )
                    }

                    item {
                        MyAppsView(
                            heading = "My Apps",
                            apps = screenState.portfolioData.apps
                        ) {

                        }
                    }
                }
            }
        }
    }
}