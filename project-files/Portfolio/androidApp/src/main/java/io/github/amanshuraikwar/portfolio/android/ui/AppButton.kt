package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.amanshuraikwar.portfolio.R
import io.github.amanshuraikwar.portfolio.model.AppLink
import java.util.*

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    description: String,
    links: List<AppLink>,
    onAppLinkClick: (url: String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = { })
        ) {
            Surface(
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(8.dp)
                    .size(72.dp),
                shape = MaterialTheme.shapes.small,
                elevation = 2.dp,
                color = MaterialTheme.colors.primary
            ) {
                Icon(
                    painter = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .size(72.dp)
                        .padding(12.dp),
                    tint = MaterialTheme.colors.onPrimary
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(
                    text = title
                        .replaceFirstChar {
                            if (it.isLowerCase())
                                it.titlecase(Locale.getDefault())
                            else
                                it.toString()
                        },
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = description,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                )

                LazyRow(
                    modifier = Modifier
                        .align(Alignment.End),
                    contentPadding = PaddingValues(
                        top = 4.dp
                    )
                ) {
                    links.forEach { appLink ->
                        item {
                            when (appLink) {
                                is AppLink.Github -> {
                                    AppLinkButton(
                                        modifier = Modifier
                                            .padding(4.dp),
                                        text = "Source Code",
                                        icon = painterResource(R.drawable.ic_github_24),
                                        onClick = {
                                            onAppLinkClick(appLink.repoUrl)
                                        }
                                    )
                                }
                                is AppLink.Other -> {
                                    AppLinkButton(
                                        modifier = Modifier
                                            .padding(4.dp),
                                        text = "More Info",
                                        icon = rememberVectorPainter(image = Icons.Rounded.Link),
                                        onClick = {
                                            onAppLinkClick(appLink.url)
                                        }
                                    )
                                }
                                is AppLink.PlayStore -> {
                                    AppLinkButton(
                                        modifier = Modifier
                                            .padding(4.dp),
                                        text = "Play Store",
                                        icon = painterResource(R.drawable.ic_google_play_24),
                                        onClick = {
                                            onAppLinkClick(appLink.playStoreListingUrl)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}