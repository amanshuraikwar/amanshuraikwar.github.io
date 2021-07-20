package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            PortfolioLinkButton(
                modifier = Modifier.padding(
                    bottom = 4.dp,
                ),
                text = linkData.name,
                onClick = {
                    onLinkClick(linkData.url)
                }
            )
        }
    }
}