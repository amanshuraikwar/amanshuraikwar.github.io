package io.github.amanshuraikwar.portfolio.android.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import io.github.amanshuraikwar.portfolio.R
import io.github.amanshuraikwar.portfolio.android.home.view.*
import io.github.amanshuraikwar.portfolio.android.theme.disabled
import io.github.amanshuraikwar.portfolio.android.ui.PortfolioLinkButton

@Composable
fun rememberStickyHeaderElevation(
    index: Int,
    lazyListState: LazyListState
): Dp {
    val isSticking by derivedStateOf { lazyListState.firstVisibleItemIndex >= index }
    val elevation by animateDpAsState(
        targetValue = if (isSticking) {
            4.dp
        } else {
            0.dp
        }
    )
    return elevation
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
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
            val listState = rememberLazyListState()

            LazyColumn(
                modifier,
                state = listState,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.systemBars,
                    additionalBottom = 128.dp
                )
            ) {
                item {
                    HeroView(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        name = screenState.portfolioData.name,
                        onUrlClick = onLinkClick,
                        intro = screenState.portfolioData.intro,
                    )
                }

                stickyHeader("projects") {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.surface,
                        elevation = rememberStickyHeaderElevation(1, listState)
                    ) {
                        SectionHeaderView(
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp
                                )
                                .statusBarsPadding()
                                .padding(
                                    top = 16.dp,
                                    bottom = 16.dp
                                ),
                            heading = "Projects"
                        )
                    }
                }

                screenState.portfolioData.projects.forEach { appData ->
                    item {
                        ProjectView(
                            Modifier
                                .padding(vertical = 16.dp)
                                .padding(horizontal = 16.dp),
                            projectData = appData,
                            onAppLinkClick = onLinkClick
                        )
                    }
                }

                stickyHeader("experience") {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.surface,
                        elevation = rememberStickyHeaderElevation(
                            screenState.portfolioData.projects.size + 2,
                            listState
                        )
                    ) {
                        SectionHeaderView(
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp
                                )
                                .statusBarsPadding()
                                .padding(
                                    top = 16.dp,
                                    bottom = 16.dp
                                ),
                            heading = "Experience"
                        )
                    }
                }

                screenState.portfolioData.background.forEach { experienceData ->
                    item {
                        ExperienceItemView(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(vertical = 16.dp),
                            backgroundData = experienceData,
                        )
                    }
                }

                stickyHeader("mylinks") {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.surface,
                        elevation = rememberStickyHeaderElevation(
                            screenState.portfolioData.background.size +
                                    screenState.portfolioData.projects.size + 3,
                            listState
                        )
                    ) {
                        SectionHeaderView(
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp
                                )
                                .statusBarsPadding()
                                .padding(
                                    top = 16.dp,
                                    bottom = 16.dp
                                ),
                            heading = "My Links"
                        )
                    }
                }

                screenState.portfolioData.links.forEach { linkData ->
                    item {
                        val painter: Painter = when (linkData.id) {
                            "resume" -> rememberVectorPainter(image = Icons.Rounded.Article)
                            "playstore" -> painterResource(R.drawable.ic_google_play_24)
                            "unsplash" -> rememberVectorPainter(image = Icons.Rounded.CameraAlt)
                            "github" -> painterResource(R.drawable.ic_github_24)
                            "linkedin" -> painterResource(R.drawable.ic_linkedin_24)
                            "medium" -> painterResource(R.drawable.ic_medium_24)
                            "twitter" -> painterResource(R.drawable.ic_twitter_24)
                            "instagram" -> painterResource(R.drawable.ic_instagram_24)
                            else -> rememberVectorPainter(image = Icons.Rounded.Link)
                        }

                        PortfolioLinkButton(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp),
                            icon = painter,
                            text = linkData.title,
                            onClick = {
                                onLinkClick(linkData.url)
                            }
                        )
                    }
                }

                item {
                    ThemeSwitchView(
                        modifier = Modifier
                            .padding(16.dp)
                            .statusBarsPadding(),
                        isDarkTheme = isDarkTheme,
                        onThemeSwitchValueChange = onThemeSwitchValueChange
                    )
                }

                item {
                    Text(
                        text = screenState.portfolioData.madeWith,
                        modifier = Modifier
                            .padding(16.dp)
                            .statusBarsPadding(),
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onSurface.disabled
                    )
                }
            }
        }
    }
}