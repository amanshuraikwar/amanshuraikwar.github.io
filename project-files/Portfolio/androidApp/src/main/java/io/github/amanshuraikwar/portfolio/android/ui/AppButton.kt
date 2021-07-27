package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
        color = MaterialTheme.colors.primary.copy(alpha = 0.08f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { })
        ) {
            Surface(
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
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
                    .padding(vertical = 16.dp)
                    .padding(end = 16.dp),
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
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = description,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp),
                ) {
                    links.forEach { appLink ->
                        AppLinkButton(
                            modifier = Modifier.padding(start = 8.dp),
                            icon = when (appLink) {
                                is AppLink.Github ->
                                    painterResource(R.drawable.ic_github_24)
                                is AppLink.Other ->
                                    rememberVectorPainter(image = Icons.Rounded.Link)
                                is AppLink.PlayStore ->
                                    painterResource(R.drawable.ic_google_play_24)
                                is AppLink.Download ->
                                    rememberVectorPainter(image = Icons.Rounded.Download)
                            },
                            onClick = {
                                when (appLink) {
                                    is AppLink.Github ->
                                        onAppLinkClick(appLink.repoUrl)
                                    is AppLink.Other ->
                                        onAppLinkClick(appLink.url)
                                    is AppLink.PlayStore ->
                                        onAppLinkClick(appLink.playStoreListingUrl)
                                    is AppLink.Download ->
                                        onAppLinkClick(appLink.downloadUrl)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}