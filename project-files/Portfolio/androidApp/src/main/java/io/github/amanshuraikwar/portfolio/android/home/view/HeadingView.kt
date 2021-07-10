package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HeadingView(
    name: String,
    intro: String,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 48.dp),
            text = name,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Medium
        )

        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = intro,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1,
        )
    }
}