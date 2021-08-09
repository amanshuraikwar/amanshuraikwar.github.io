package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.Project
import com.sample.components.SectionHeader
import com.sample.style.WtCols
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.model.AppData
import org.jetbrains.compose.web.dom.Div

@Composable
fun Projects(
    isDarkTheme: Boolean,
    apps: List<AppData>
) {
    SectionHeader(
        attrs = {
            classes(
                WtOffsets.wtTopOffset96,
                WtOffsets.wtTopOffsetSm48,
            )
        },
        title = "Projects"
    )

    Div(
        attrs = {
            classes(
                WtRows.wtRow,
                WtOffsets.wtTopOffset48,
                WtOffsets.wtTopOffsetSm24
            )
        }
    ) {
        if (isDarkTheme) {
            apps.forEachIndexed { index, appData ->
                Project(
                    attrs = {
                        classes(
                            WtCols.wtCol6,
                            WtCols.wtColSm12,
                        )

                        if (index % 2 == 0) {
                            classes(
                                WtContent.projectRightPadding
                            )
                        }

                        if (index % 2 != 0) {
                            classes(
                                WtContent.projectLeftPadding
                            )
                        }

                        if (index != apps.size - 1) {
                            classes(
                                WtContent.projectBottomPadding
                            )
                        }
                    },
                    appData = appData,
                    isDarkTheme = true,
                )
            }
        } else {
            apps.forEachIndexed { index, appData ->
                Project(
                    attrs = {
                        classes(
                            WtCols.wtCol6,
                            WtCols.wtColSm12,
                        )

                        if (index % 2 == 0) {
                            classes(
                                WtContent.projectRightPadding
                            )
                        }

                        if (index % 2 != 0) {
                            classes(
                                WtContent.projectLeftPadding
                            )
                        }

                        if (index != apps.size - 1) {
                            classes(
                                WtContent.projectBottomPadding
                            )
                        }
                    },
                    appData = appData,
                    isDarkTheme = false,
                )
            }
        }
    }
}