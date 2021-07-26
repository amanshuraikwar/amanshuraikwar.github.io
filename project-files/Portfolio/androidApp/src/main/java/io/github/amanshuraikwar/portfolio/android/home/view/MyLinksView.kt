package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Shop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.amanshuraikwar.portfolio.R
import io.github.amanshuraikwar.portfolio.android.ui.PortfolioLinkButton
import io.github.amanshuraikwar.portfolio.model.LinkData

@Composable
fun MyLinksView(
    heading: String,
    links: List<LinkData>,
    onLinkClick: (url: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 48.dp, bottom = 16.dp),
            text = heading,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6,
        )

        links.forEach { linkData ->
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
                icon = painter,
                text = linkData.title,
                onClick = {
                    onLinkClick(linkData.url)
                }
            )
        }
    }
}