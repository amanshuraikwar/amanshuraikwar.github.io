package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun HeadingView(
    name: String,
    url: String,
) {
    val scrimColor = Color(0XFF212121).copy(alpha = 0.48f)

    Box {
        Image(
            painter = rememberImagePainter(
                data = url,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    1f,
                    matchHeightConstraintsFirst = false
                ),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .background(scrimColor)
                .fillMaxWidth()
                .aspectRatio(
                    1f,
                    matchHeightConstraintsFirst = false
                )
        ) {}

        if (MaterialTheme.colors.isLight) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.background
                    )
                    .fillMaxWidth()
                    .statusBarsHeight()
            ) {}
        }

        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(16.dp),
            text = "PHOTO FROM\nUNSPLASH",
            color = Color.White,
            style = MaterialTheme.typography.overline.copy(
                shadow = Shadow(
                    Color(0XFF212121).copy(alpha = 0.84f),
                    offset = Offset(1f, 1f),
                    blurRadius = 4f
                )
            ),
            textAlign = TextAlign.End
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            text = name,
            color = Color.White,
            style = MaterialTheme.typography.h3.copy(
                shadow = Shadow(
                    Color(0XFF212121).copy(alpha = 0.84f),
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            ),
            fontWeight = FontWeight.Medium,
        )
    }
}