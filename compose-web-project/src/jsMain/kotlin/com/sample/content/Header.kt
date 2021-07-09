package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Section

@Composable
fun Header() {
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
                MyName()
            }

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
                MyIntro()
            }
        }
    }
}