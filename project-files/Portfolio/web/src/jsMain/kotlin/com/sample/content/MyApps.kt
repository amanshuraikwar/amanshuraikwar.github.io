package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.HrefButton
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.model.LinkData
import com.sample.components.Button
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.*

@Composable
fun MyApps(
    heading: String = "My Apps",
    onAppClick: (String) -> Unit,
) {
    Section {
        Div(
            attrs = {
                classes(WtContainer.wtContainer)
            }
        ) {
            Div(
                attrs = {
                    classes(
                        WtRows.wtRow,
                        WtRows.wtRowSizeM,
                        WtOffsets.wtTopOffset96,
                        WtOffsets.wtTopOffsetSm48
                    )
                }
            ) {
                H2(
                    attrs = {
                        classes(WtTexts.wtH2)
                        style {
                            width(100.percent)
                        }
                    }
                ) {
                    Text(heading)
                }

                P {
                    Button(
                        onClick = {
                            onAppClick("nextbus")
                        }
                    ) {
                        Text("Next Bus SG")
                    }
                }
            }
        }
    }
}