package io.github.amanshuraikwar.portfolio.android.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRightAlt
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import io.github.amanshuraikwar.portfolio.android.theme.secondary
import io.github.amanshuraikwar.portfolio.model.ExperienceData

@Composable
fun ExperienceItemView(
    modifier: Modifier = Modifier,
    experienceData: ExperienceData
) {
    Column(modifier) {
        Text(
            text = experienceData.title,
            style = MaterialTheme.typography.h6
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(16.dp),
                imageVector = Icons.Rounded.Place,
                contentDescription = "Location",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface, BlendMode.SrcIn)
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = experienceData.location,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface.secondary
            )
        }

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Rounded.DateRange,
                contentDescription = "Date Range",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface, BlendMode.SrcIn)
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = experienceData.dateRange,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface.secondary
            )
        }

        experienceData.content.forEach { contentItem ->
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .size(16.dp),
                    imageVector = Icons.Rounded.ArrowRightAlt,
                    contentDescription = "Experience Item",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface, BlendMode.SrcIn)
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = contentItem,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface.secondary
                )
            }
        }
    }
}