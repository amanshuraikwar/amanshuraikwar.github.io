package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.components.HrefButton
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import io.github.amanshuraikwar.portfolio.model.LinkData
import org.jetbrains.compose.web.dom.*

@Composable
fun MyLinks(
    heading: String,
    links: List<LinkData>
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
                H2(attrs = { classes(WtTexts.wtH2) }) {
                    Text(heading)
                }

                P {
                    for ((name, url) in links) {
                        HrefButton(text = name, link = url)
                    }
                }
            }
        }
    }
}