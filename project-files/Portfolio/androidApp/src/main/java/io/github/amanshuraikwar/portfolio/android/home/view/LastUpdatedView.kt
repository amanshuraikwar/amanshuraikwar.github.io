package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LastUpdatedView(
    message: String,
) {
    Text(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp),
        text = message,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body1,
    )
}