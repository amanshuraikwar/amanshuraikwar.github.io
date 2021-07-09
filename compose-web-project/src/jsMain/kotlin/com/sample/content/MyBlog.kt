package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.WtContainer
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import com.sample.style.WtTexts
import org.jetbrains.compose.web.dom.*

@Composable
fun MyBlog() {
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
                    Text("I also write sometimes")
                }


            }
        }
    }
}