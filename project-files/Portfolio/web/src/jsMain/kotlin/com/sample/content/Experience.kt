package com.sample.content

import androidx.compose.runtime.*
import com.sample.components.*
import com.sample.style.WtCols
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.model.ExperienceData
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
fun Experience(
    experience: List<ExperienceData>,
    isDarkTheme: Boolean,
) {
    var displayedList by remember { mutableStateOf(experience.take(2)) }

    SectionHeader(
        attrs = {
            classes(
                WtOffsets.wtTopOffset96,
                WtOffsets.wtTopOffsetSm48,
            )
        },
        title = "Experience"
    )

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
                        WtOffsets.wtTopOffsetSm24
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
                    WtOffsets.wtTopOffsetSm24,
                    WtOffsets.wtTopOffset48
                )
                style {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            if (displayedList.size == experience.size) {
                TextButton(
                    text = "SHOW LESS",
                    buttonStyle = ButtonStyle.SOLID,
                    buttonSize = ButtonSize.NORMAL,
                    onClick = {
                        displayedList = experience.take(2)
                    }
                )
            } else {
                TextButton(
                    text = "SHOW MORE",
                    buttonStyle = ButtonStyle.SOLID,
                    buttonSize = ButtonSize.NORMAL,
                    onClick = {
                        displayedList = experience
                    }
                )
            }
        }
    }
}