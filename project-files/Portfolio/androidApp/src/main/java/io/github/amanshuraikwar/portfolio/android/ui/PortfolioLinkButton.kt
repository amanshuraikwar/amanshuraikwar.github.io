package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun PortfolioLinkButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.primary.copy(alpha = 0.02f)
    ) {
        FillFirstRow(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        ) {
            Row {
                Surface(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.small,
                    elevation = 2.dp,
                    color = MaterialTheme.colors.primary
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = text,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    text = text
                        .replaceFirstChar {
                            if (it.isLowerCase())
                                it.titlecase(Locale.getDefault())
                            else
                                it.toString()
                        },
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1
                )
            }

            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(end = 8.dp),
                imageVector = Icons.Rounded.OpenInNew,
                contentDescription = "Arrow Right",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}