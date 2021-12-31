package com.sample.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sample.components.ButtonSize
import com.sample.components.ButtonStyle
import com.sample.components.ExperienceItem
import com.sample.components.TextButton
import com.sample.style.WtCols
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.model.BackgroundData
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.dom.Div

@Composable
fun Experience(
    background: List<BackgroundData>,
    isDarkTheme: Boolean,
) {
    var displayedList by remember { mutableStateOf(background.take(2)) }

    Div(
        attrs = {
            classes(
                WtRows.wtRow,
            )
        }
    ) {
        displayedList.forEachIndexed { _, experienceData ->
            ExperienceItem(
                attrs = {
                    classes(WtCols.wtCol12)
                    classes(
                        WtOffsets.wtTopOffset48,
                    )
                },
                data = experienceData,
                isDarkTheme = isDarkTheme,
            )
        }

        Div(
            attrs = {
                classes(
                    WtCols.wtCol12,
                    WtOffsets.wtTopOffset48
                )
                style {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            if (displayedList.size == background.size) {
                TextButton(
                    text = "SHOW LESS",
                    buttonStyle = ButtonStyle.SOLID,
                    buttonSize = ButtonSize.NORMAL,
                    onClick = {
                        displayedList = background.take(2)
                    }
                )
            } else {
                TextButton(
                    text = "SHOW MORE",
                    buttonStyle = ButtonStyle.SOLID,
                    buttonSize = ButtonSize.NORMAL,
                    onClick = {
                        displayedList = background
                    }
                )
            }
        }
    }
}