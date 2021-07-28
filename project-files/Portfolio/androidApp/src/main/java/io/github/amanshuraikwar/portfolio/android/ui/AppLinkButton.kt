package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun AppLinkButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    bgColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.13f),
    iconTint: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit
) {
    CompositionLocalProvider(
        LocalIndication provides rememberRipple(color = MaterialTheme.colors.primary)
    ) {
        Surface(
            modifier = modifier,
            shape = MaterialTheme.shapes.small,
            color = bgColor,
        ) {
            Icon(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(8.dp)
                    .size(24.dp),
                painter = icon,
                contentDescription = "app-link-icon",
                tint = iconTint
            )
        }
    }
}