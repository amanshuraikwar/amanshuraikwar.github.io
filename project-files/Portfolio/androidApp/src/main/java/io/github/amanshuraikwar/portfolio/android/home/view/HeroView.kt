package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import io.github.amanshuraikwar.portfolio.android.theme.secondary
import io.github.amanshuraikwar.portfolio.android.ui.ProjectLinkButton
import io.github.amanshuraikwar.portfolio.android.util.crop

@Composable
fun HeroView(
    modifier: Modifier = Modifier,
    name: String,
    intro: String,
    onUrlClick: (url: String) -> Unit
) {
    val scrimColor = Color(0XFF212121).copy(alpha = 0.48f)

    var url: String by remember {
        mutableStateOf(imageUrlList.random())
    }

    var scaleFactor: Float by remember {
        mutableStateOf(1.6f)
    }

    LaunchedEffect(url) {
        animate(
            1.6f,
            1f,
            animationSpec = tween(10000)
        ) { value, _ ->
            scaleFactor = value
        }

        animate(
            1f,
            0f,
            animationSpec = tween(900)
        ) { value, _ ->
            scaleFactor = value
        }

        var newUrl: String
        do {
            newUrl = imageUrlList.random()
        } while (newUrl == url)
        url = newUrl
    }

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.primary.copy(alpha = 0.08f)
    ) {
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
                contentScale = crop(scaleFactor)
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

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                text = "PHOTO FROM\nUNSPLASH",
                color = Color.White.secondary,
                style = MaterialTheme.typography.overline.copy(
                    shadow = Shadow(
                        Color(0XFF212121).copy(alpha = 0.84f),
                        offset = Offset(1f, 1f),
                        blurRadius = 2f
                    )
                ),
                textAlign = TextAlign.Start
            )

            ProjectLinkButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                icon = rememberVectorPainter(
                    image = Icons.Rounded.Download
                ),
                onClick = {
                    onUrlClick(url)
                },
                bgColor = Color.White.copy(alpha = 0.13f),
                iconTint = Color.White
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = name.replace(" ", "\n"),
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

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = intro,
                    color = Color.White.secondary,
                    style = MaterialTheme.typography.body1.copy(
                        shadow = Shadow(
                            Color(0XFF212121).copy(alpha = 0.84f),
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    ),
                )
            }
        }
    }
}

private val imageUrlList = listOf(
    "https://images.unsplash.com/photo-1513232457641-78faae852ad1?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-RTkejO7v2R8-unsplash.jpg",
    "https://images.unsplash.com/photo-1541769938-ea699dc083c0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-bxaVhi3CJOs-unsplash.jpg",
    "https://images.unsplash.com/photo-1506757171859-d7ba4a0c9a94?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-yHwVM2em7Nw-unsplash.jpg",
    "https://images.unsplash.com/photo-1502342556579-b648d39eea0d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-quA2agQuFyo-unsplash.jpg",
    "https://images.unsplash.com/photo-1501067486956-e9a0bd9c2b46?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-ZHrbVmVu3Qc-unsplash.jpg",
    "https://images.unsplash.com/photo-1500903443203-462f7a15fc65?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-Mb4KB_XzF3g-unsplash.jpg",
    "https://images.unsplash.com/photo-1491938833905-38e9abfe92db?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-cSsgcUMUESU-unsplash.jpg",
    "https://images.unsplash.com/photo-1579419937634-b87791f5ea76?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-hD1JFI6oFTk-unsplash.jpg",
    "https://images.unsplash.com/photo-1626622244039-9d47ab5ded20?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-lWRXSkOEFvs-unsplash.jpg",
    "https://images.unsplash.com/photo-1539527360263-a95d6f4ffb6a?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-qCTi9NWTel0-unsplash.jpg",
    "https://images.unsplash.com/photo-1534473137053-538415f7f534?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-AmijovYKbn8-unsplash.jpg",
    "https://images.unsplash.com/photo-1507729490900-eaad637d462b?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-nZv1QdQ8vwM-unsplash.jpg",
    "https://images.unsplash.com/photo-1512235761740-a609c3565912?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=amanshu-raikwar-vbsz5wF43Pg-unsplash.jpg"
)