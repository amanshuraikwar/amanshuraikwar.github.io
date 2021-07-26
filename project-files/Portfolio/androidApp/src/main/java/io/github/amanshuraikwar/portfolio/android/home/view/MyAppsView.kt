package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.amanshuraikwar.portfolio.R
import io.github.amanshuraikwar.portfolio.android.ui.AppButton
import io.github.amanshuraikwar.portfolio.model.AppData

@Composable
fun MyAppsView(
    heading: String,
    apps: List<AppData>,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 48.dp, bottom = 16.dp),
            text = heading,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h5,
        )

        apps.forEach { appData ->
            val painter: Painter = when (appData.id) {
                "nextbus" -> painterResource(R.drawable.ic_nextbus_74)
                else -> rememberVectorPainter(image = Icons.Rounded.Link)
            }
            AppButton(
                icon = painter,
                title = appData.title,
                description = appData.description,
                onClick = {
                }
            )
        }
    }
}