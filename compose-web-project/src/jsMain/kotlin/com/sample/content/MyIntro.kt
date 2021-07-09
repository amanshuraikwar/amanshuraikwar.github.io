package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.WtOffsets
import com.sample.style.WtTexts
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun MyIntro() {
    Span(
        attrs = {
            classes(WtTexts.wtText1)
        }
    ) {
        Text("I'm a Software Engineer, building stuff for the lil green droid.")
    }
}