@file:Suppress("KDocUnresolvedReference")

package io.github.amanshuraikwar.portfolio.android.ui

//import androidx.compose.ui.graphics.vector.AnimatedImageVector
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

enum class ThemeSwitchValue {
    DARK, LIGHT
}

/**
 * Taken from [androidx.compose.material.Swipeable].
 *
 * Why:
 * Create and [remember] a [SwipeableState] which is kept in sync with another state, i.e.:
 *  1. Whenever the [value] changes, the [SwipeableState] will be animated to that new value.
 *  2. Whenever the value of the [SwipeableState] changes (e.g. after a swipe), the owner of the
 *  [value] will be notified to update their state to the new value of the [SwipeableState] by
 *  invoking [onValueChange]. If the owner does not update their state to the provided value for
 *  some reason, then the [SwipeableState] will perform a rollback to the previous, correct value.
 *
 * @author amanshuraikwar
 * @since 20 Jul 2021 04:09:34 PM
 */
@Composable
@ExperimentalMaterialApi
internal fun <T : Any> rememberSwipeableStateFor(
    value: T,
    onValueChange: (T) -> Unit,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec
): SwipeableState<T> {
    val swipeableState = remember {
        SwipeableState(
            initialValue = value,
            animationSpec = animationSpec,
            confirmStateChange = { true }
        )
    }
    val forceAnimationCheck = remember { mutableStateOf(false) }
    LaunchedEffect(value, forceAnimationCheck.value) {
        if (value != swipeableState.currentValue) {
            swipeableState.animateTo(value)
        }
    }
    DisposableEffect(swipeableState.currentValue) {
        if (value != swipeableState.currentValue) {
            onValueChange(swipeableState.currentValue)
            forceAnimationCheck.value = !forceAnimationCheck.value
        }
        onDispose { }
    }
    return swipeableState
}


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun ThemeSwitch(
    modifier: Modifier = Modifier,
    value: ThemeSwitchValue,
    backgroundColor: Color = MaterialTheme.colors.primary,
    handleColor: Color = MaterialTheme.colors.onPrimary,
    iconTint: Color = MaterialTheme.colors.primary,
    lightThemeIcon: ImageVector = Icons.Default.LightMode,
    darkThemeIcon: ImageVector = Icons.Default.DarkMode,
    backgroundShape: Shape = MaterialTheme.shapes.small,
    handleShape: Shape = backgroundShape,
    onValueChange: (newValue: ThemeSwitchValue) -> Unit = {},
) {
    val handleSize = 28f
    val padding = 2f

    val swipeableState = rememberSwipeableStateFor(
        value = value,
        onValueChange = onValueChange,
        TweenSpec(durationMillis = 100)
    )

    val iconPainter = rememberVectorPainter(
        if (swipeableState.offset.value >= handleSize / 2f) {
            darkThemeIcon
        } else {
            lightThemeIcon
        }
    )

    val anchors = mapOf(0f to ThemeSwitchValue.LIGHT, handleSize to ThemeSwitchValue.DARK)

    Canvas(
        modifier = modifier
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .size(
                width = ((handleSize + padding) * 2).dp,
                height = (handleSize + padding * 2).dp
            ),
    ) {
        // TODO-amanshuraikwar (15 Aug 2021 04:02:28 PM): cache outline
        drawOutline(
            outline = backgroundShape.createOutline(
                size = size,
                layoutDirection = layoutDirection,
                this
            ),
            color = backgroundColor,
        )

        translate(
            top = padding.dp.toPx(),
            left = (swipeableState.offset.value + padding).dp.toPx(),
        ) {
            // TODO-amanshuraikwar (15 Aug 2021 04:02:28 PM): cache outline
            drawOutline(
                outline = handleShape.createOutline(
                    size = Size(handleSize.dp.toPx(), handleSize.dp.toPx()),
                    layoutDirection = layoutDirection,
                    this
                ),
                color = handleColor,
            )
        }

        translate(
            (swipeableState.offset.value + padding * 2).dp.toPx(),
            (padding * 2).dp.toPx()
        ) {
            with(iconPainter) {
                draw(
                    size = Size(
                        (handleSize - padding * 2).dp.toPx(),
                        (handleSize - padding * 2).dp.toPx()
                    ),
                    colorFilter = ColorFilter.tint(iconTint, BlendMode.SrcIn)
                )
            }
        }
    }
}

/*
TODO-amanshuraikwar (28 Jul 2021 05:27:11 PM):
 commenting out until animatedVectorDrawable is added back to compose

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun ThemeSwitch(
    modifier: Modifier = Modifier,
    value: ThemeSwitchValue,
    backgroundColor: Color = MaterialTheme.colors.primary,
    handleColor: Color = MaterialTheme.colors.onPrimary,
    iconTint: Color = MaterialTheme.colors.primary,
    avdIcon: AnimatedImageVector,
    avdEndValue: ThemeSwitchValue,
    onValueChange: (newValue: ThemeSwitchValue) -> Unit = {},
) {
    val handleSize = 28f
    val padding = 2f

    val swipeableState = rememberSwipeableStateFor(
        value = value,
        onValueChange = onValueChange,
        TweenSpec(durationMillis = 100)
    )

    val anchors = mapOf(0f to ThemeSwitchValue.LIGHT, handleSize to ThemeSwitchValue.DARK)

    val avdPainter = avdIcon.painterFor(
        atEnd = when (avdEndValue) {
            ThemeSwitchValue.DARK -> {
                swipeableState.offset.value >= handleSize / 2f
            }
            ThemeSwitchValue.LIGHT -> {
                swipeableState.offset.value <= handleSize / 2f
            }
        }
    )

    Canvas(
        modifier = modifier
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .size(
                width = ((handleSize + padding) * 2).dp,
                height = (handleSize + padding * 2).dp
            ),
    ) {
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset.Zero,
            size = size,
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )

        drawRoundRect(
            color = handleColor,
            topLeft = Offset(
                (swipeableState.offset.value + padding).dp.toPx(),
                padding.dp.toPx()
            ),
            size = Size(handleSize.dp.toPx(), handleSize.dp.toPx()),
            cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
        )

        translate(
            (swipeableState.offset.value + padding * 2).dp.toPx(),
            (padding * 2).dp.toPx()
        ) {
            with(avdPainter) {
                draw(
                    size = Size(
                        (handleSize - padding * 2).dp.toPx(),
                        (handleSize - padding * 2).dp.toPx()
                    ),
                    colorFilter = ColorFilter.tint(iconTint, BlendMode.SrcIn)
                )
            }
        }
    }
}
 */