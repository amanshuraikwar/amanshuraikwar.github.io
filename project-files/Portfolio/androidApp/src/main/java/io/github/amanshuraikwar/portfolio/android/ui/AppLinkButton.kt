package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun AppLinkButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.primary,
        elevation = 2.dp
    ) {
        Row(
            modifier = modifier
                .clickable(onClick = onClick)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                text = text
                    .replaceFirstChar {
                        if (it.isLowerCase())
                            it.titlecase(Locale.getDefault())
                        else
                            it.toString()
                    },
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1
            )

            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(start = 8.dp)
                    .size(16.dp),
                painter = icon,
                contentDescription = text,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}