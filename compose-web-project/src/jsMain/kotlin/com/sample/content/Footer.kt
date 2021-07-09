package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.*
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.*

@Composable
fun Footer() {
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
                Span(
                    attrs = {
                        classes(WtTexts.wtText1)
                    }
                ) {
                    Text("Last updated on 9 July 2021 :)")
                }

                Br()

                Span(
                    attrs = {
                        classes(
                            WtTexts.wtText2,
                            WtOffsets.wtTopOffset24,
                            WtOffsets.wtTopOffsetSm12
                        )
                    }
                ) {
                    Text("Made with Jetpack Compose for Web using the web_landing sample")
                }
            }
        }
    }
}