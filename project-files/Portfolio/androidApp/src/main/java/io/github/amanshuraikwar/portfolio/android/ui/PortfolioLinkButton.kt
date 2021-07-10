package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun PortfolioLinkButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(
                color = MaterialTheme.colors.onBackground
            )
            .padding(8.dp),
        text = text.uppercase(Locale.ROOT),
        color = MaterialTheme.colors.background,
    )
}