package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FetchingView() {
    Text(
        text = "Compiling...",
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 48.dp
            )
    )
}