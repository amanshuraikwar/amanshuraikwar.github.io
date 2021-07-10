package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun MadeWith(
    message: String
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
                        WtOffsets.wtTopOffset24,
                        WtOffsets.wtTopOffsetSm12
                    )
                }
            ) {
                Span(
                    attrs = {
                        classes(WtTexts.wtText2)
                    }
                ) {
                    Text(message)
                }
            }
        }
    }
}