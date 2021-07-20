package io.github.amanshuraikwar.portfolio.android.ui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
        if (swipeableState.offset.value >= 24) {
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
        drawRect(
            color = backgroundColor,
            topLeft = Offset.Zero,
            size = size
        )

        drawRect(
            color = handleColor,
            topLeft = Offset(
                (swipeableState.offset.value + padding).dp.toPx(),
                padding.dp.toPx()
            ),
            size = Size(handleSize.dp.toPx(), handleSize.dp.toPx())
        )

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