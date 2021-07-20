package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.IntOffset

@Composable
fun FillFirstColumn(
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = children
    ) { measurables, constraints ->
        val placeables = mutableListOf<Placeable>()
        var width = 0

        if (measurables.size == 1) {
            placeables.add(
                measurables[0]
                    .measure(
                        constraints.copy(
                            minHeight = constraints.maxHeight,
                            maxHeight = constraints.maxHeight
                        )
                    )
                    .also { placeable ->
                        width = placeable.width
                    }
            )
        } else if (measurables.size > 1) {
            var childrenHeight = 0

            for (childIndex in 1 until measurables.size) {
                placeables.add(
                    measurables[childIndex]
                        .measure(
                            constraints.copy(minHeight = 0)
                        )
                        .also { placeable ->
                            width = placeable.width.coerceAtLeast(width)
                            childrenHeight += placeable.height
                        }
                )
            }

            placeables.add(
                0,
                measurables[0]
                    .measure(
                        constraints.copy(
                            minHeight = constraints.maxHeight - childrenHeight,
                            maxHeight = constraints.maxHeight - childrenHeight,
                        )
                    )
                    .also { placeable ->
                        width = placeable.width.coerceAtLeast(width)
                    }
            )
        }

        val height = constraints.maxHeight

        layout(width = width, height = height) {
            var y = 0
            placeables.forEach { placeable ->
                placeable.place(
                    IntOffset(0, y)
                )
                y += placeable.height
            }
        }
    }
}