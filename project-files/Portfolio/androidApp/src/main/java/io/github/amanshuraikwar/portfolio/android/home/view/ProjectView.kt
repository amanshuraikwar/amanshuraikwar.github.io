package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import io.github.amanshuraikwar.portfolio.R
import io.github.amanshuraikwar.portfolio.android.theme.secondary
import io.github.amanshuraikwar.portfolio.android.ui.ProjectLinkButton
import io.github.amanshuraikwar.portfolio.model.AppData
import io.github.amanshuraikwar.portfolio.model.AppLink
import java.util.*

@Composable
fun ProjectView(
    modifier: Modifier = Modifier,
    appData: AppData,
    onAppLinkClick: (url: String) -> Unit
) {
    Column(
        modifier = modifier,
    ) {

        Image(
            painter = rememberImagePainter(
                data = appData.artUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    1.5f,
                    matchHeightConstraintsFirst = false
                )
                .clip(
                    MaterialTheme.shapes.medium
                ),
            contentScale = ContentScale.Fit
        )

        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = appData.title
                .replaceFirstChar {
                    if (it.isLowerCase())
                        it.titlecase(Locale.getDefault())
                    else
                        it.toString()
                },
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h5,
        )

        Text(
            modifier = Modifier
                .padding(top = 12.dp),
            text = appData.description,
            color = MaterialTheme.colors.onSurface.secondary,
            style = MaterialTheme.typography.body1
        )

        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp),
        ) {
            appData.appLinks.forEach { appLink ->
                ProjectLinkButton(
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