package io.github.amanshuraikwar.portfolio.android.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import io.github.amanshuraikwar.portfolio.R
import io.github.amanshuraikwar.portfolio.android.home.view.FetchingView
import io.github.amanshuraikwar.portfolio.android.home.view.HeadingView
import io.github.amanshuraikwar.portfolio.android.home.view.ThemeSwitchView
import io.github.amanshuraikwar.portfolio.android.ui.AppButton
import io.github.amanshuraikwar.portfolio.android.ui.PortfolioLinkButton

private val imageUrlList = listOf(
    "https://images.unsplash.com/photo-1513232457641-78faae852ad1?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-RTkejO7v2R8-unsplash.jpg",
    "https://images.unsplash.com/photo-1541769938-ea699dc083c0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-bxaVhi3CJOs-unsplash.jpg",
    "https://images.unsplash.com/photo-1506757171859-d7ba4a0c9a94?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-yHwVM2em7Nw-unsplash.jpg",
    "https://images.unsplash.com/photo-1502342556579-b648d39eea0d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-quA2agQuFyo-unsplash.jpg",
    "https://images.unsplash.com/photo-1501067486956-e9a0bd9c2b46?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-ZHrbVmVu3Qc-unsplash.jpg",
    "https://images.unsplash.com/photo-1500903443203-462f7a15fc65?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-Mb4KB_XzF3g-unsplash.jpg",
    "https://images.unsplash.com/photo-1491938833905-38e9abfe92db?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-cSsgcUMUESU-unsplash.jpg",
)

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
            val url = imageUrlList.random()

            LazyColumn(
                modifier,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.navigationBars,
                    additionalBottom = 128.dp
                )
            ) {
                item {
                    HeadingView(
                        name = screenState.portfolioData.name,
                        url = url,
                    )
                }

                item {
                    ThemeSwitchView(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 32.dp),
                        isDarkTheme = isDarkTheme,
                        onThemeSwitchValueChange = onThemeSwitchValueChange
                    )
                }

                item {
                    Text(
                        modifier = Modifier
                            .padding(top = 32.dp, bottom = 16.dp)
                            .padding(horizontal = 16.dp),
                        text = "My Links",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.h6,
                    )
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
                                .padding(bottom = 8.dp),
                            icon = painter,
                            text = linkData.title,
                            onClick = {
                                onLinkClick(linkData.url)
                            }
                        )
                    }
                }

                item {
                    Text(
                        modifier = Modifier
                            .padding(top = 32.dp, bottom = 16.dp)
                            .padding(horizontal = 16.dp),
                        text = "My Apps",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.h6,
                    )
                }

                screenState.portfolioData.apps.forEach { appData ->
                    item {
                        val painter: Painter = when (appData.id) {
                            "nextbus" -> painterResource(R.drawable.ic_nextbus_74)
                            "splash" -> rememberVectorPainter(image = Icons.Rounded.Landscape)
                            "howmuch" -> rememberVectorPainter(image = Icons.Rounded.BubbleChart)
                            else -> rememberVectorPainter(image = Icons.Rounded.Link)
                        }

                        AppButton(
                            Modifier
                                .padding(bottom = 8.dp)
                                .padding(horizontal = 8.dp),
                            icon = painter,
                            title = appData.title,
                            description = appData.description,
                            links = appData.appLinks,
                            onAppLinkClick = onLinkClick
                        )
                    }
                }
            }
        }
    }
}