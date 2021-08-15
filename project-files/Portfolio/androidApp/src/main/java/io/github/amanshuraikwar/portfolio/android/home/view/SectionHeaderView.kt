package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SectionHeaderView(
    modifier: Modifier = Modifier,
    heading: String
) {
    Text(
        modifier = modifier,
        text = heading,
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.onSurface
    )
}