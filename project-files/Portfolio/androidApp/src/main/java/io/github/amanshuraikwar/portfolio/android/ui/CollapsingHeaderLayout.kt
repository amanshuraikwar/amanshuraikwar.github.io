package io.github.amanshuraikwar.portfolio.android.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp

@Composable
fun rememberCollapsingHeaderState(): CollapsingHeaderLayoutState {
    return CollapsingHeaderLayoutState(
        headerHeight = remember {
            mutableStateOf(0.dp)
        },
        expandedHeight = remember {
            mutableStateOf(0f)
        },
        collapsedHeight = remember {
            mutableStateOf(0f)
        },
        expandFraction = remember {
            mutableStateOf(1f)
        }
    )
}

data class CollapsingHeaderLayoutState(
    val headerHeight: MutableState<Dp>,
    val collapsedHeight: MutableState<Float>,
    val expandedHeight: MutableState<Float>,
    val expandFraction: MutableState<Float>
)

@ExperimentalMaterialApi
@Composable
fun CollapsingHeaderLayout(
    modifier: Modifier = Modifier,
    collapsingHeaderLayoutState: CollapsingHeaderLayoutState,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeableState = rememberSwipeableState(
        true,
    )

    val anchors = mapOf(
        collapsingHeaderLayoutState.collapsedHeight.value to false,
        collapsingHeaderLayoutState.expandedHeight.value to true
    )

    LaunchedEffect(key1 = swipeableState.offset.value) {
        collapsingHeaderLayoutState.expandFraction.value =
            if (collapsingHeaderLayoutState.expandedHeight.value ==
                collapsingHeaderLayoutState.collapsedHeight.value
            ) {
                1f
            } else {
                1f - (collapsingHeaderLayoutState.expandedHeight.value
                        - swipeableState.offset.value) /
                        (collapsingHeaderLayoutState.expandedHeight.value -
                                collapsingHeaderLayoutState.collapsedHeight.value)
            }
    }

    Layout(
        modifier = modifier
            .nestedScroll(
                connection = object : NestedScrollConnection {
                    override suspend fun onPostFling(
                        consumed: Velocity,
                        available: Velocity
                    ): Velocity {
                        val delta = available.y
                        return if (delta > 0) {
                            swipeableState
                                .performFling(delta)
                            Velocity.Zero
                        } else {
                            Velocity.Zero
                        }
                    }

                    override fun onPostScroll(
                        consumed: Offset,
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        val delta = available.toFloat()
                        return if (delta > 0 && source == NestedScrollSource.Drag) {
                            swipeableState
                                .performDrag(delta)
                                .toOffset()
                        } else {
                            Offset.Zero
                        }
                    }

                    override suspend fun onPreFling(available: Velocity): Velocity {
                        val delta = available.y
                        return if (delta < 0) {
                            if (swipeableState.offset.value >
                                collapsingHeaderLayoutState.collapsedHeight.value
                            ) {
                                swipeableState
                                    .performFling(delta)
                                available
                            } else {
                                Velocity.Zero
                            }
                        } else {
                            Velocity.Zero
                        }
                    }

                    private fun Float.toOffset(): Offset = Offset(0f, this)

                    private fun Offset.toFloat(): Float = this.y

                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        val delta = available.toFloat()
                        return if (delta < 0 && source == NestedScrollSource.Drag) {
                            swipeableState
                                .performDrag(delta)
                                .toOffset()
                        } else {
                            Offset.Zero
                        }
                    }
                },
            )
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(1f) },
                orientation = Orientation.Vertical,
                resistance = null
            ),
        content = {
            content()
            header()
        },
    ) { measurables, constraints ->
        val headerPlaceable =
            measurables[1].measure(constraints.copy(minHeight = 0))

        val contentPlaceable =
            measurables[0].measure(
                constraints.copy(
                    minHeight = 0,
                )
            )

        layout(width = constraints.maxWidth, height = constraints.maxHeight) {
            contentPlaceable.place(
                0,
                collapsingHeaderLayoutState.headerHeight.value.roundToPx()
            )
            headerPlaceable.place(0, 0)
        }
    }
}