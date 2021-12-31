package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.Project
import com.sample.style.WtCols
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.model.ProjectData
import org.jetbrains.compose.web.dom.Div

@Composable
fun Projects(
    isDarkTheme: Boolean,
    projects: List<ProjectData>
) {
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
            projects.forEachIndexed { index, appData ->
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

                        if (index != projects.size - 1) {
                            classes(
                                WtContent.projectBottomPadding
                            )
                        }
                    },
                    projectData = appData,
                    isDarkTheme = true,
                )
            }
        } else {
            projects.forEachIndexed { index, appData ->
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

                        if (index != projects.size - 1) {
                            classes(
                                WtContent.projectBottomPadding
                            )
                        }
                    },
                    projectData = appData,
                    isDarkTheme = false,
                )
            }
        }
    }
}