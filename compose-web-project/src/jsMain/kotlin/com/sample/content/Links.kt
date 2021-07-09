package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.style.*
import org.jetbrains.compose.web.dom.*

@Composable
fun MyLinks() {
    Section(
        attrs = {
            //classes(WtSections.wtSectionBgGrayDark)
        }
    ) {
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
                    Text("You can find me at")
                }

                P {
                    for ((text, link) in linkList) {
                        HrefButton(link, text)
                    }
                }
            }
        }
    }
}

private val linkList = listOf(
    "RESUME" to "https://amanshuraikwar.github.io/assets/resume/Resume-7-Aug-2019.pdf",
    "GITHUB" to "https://github.com/amanshuraikwar",
    "LINKEDIN" to "https://www.linkedin.com/in/amanshu-raikwar-36b534103/",
    "MEDIUM" to "https://medium.com/@amanshuraikwar.in/",
    "PLAY STORE" to "https://play.google.com/store/apps/developer?id=Amanshu%20Raikwar&hl=en",
    "INSTAGRAM" to "https://instagram.com/amanshuraikwar",
    "UNSPLASH" to "https://unsplash.com/@amanshuraikwar",
    "TWITTER" to "https://twitter.com/amanshuraikwar_",
)