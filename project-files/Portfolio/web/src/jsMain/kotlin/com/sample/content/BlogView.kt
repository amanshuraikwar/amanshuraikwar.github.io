package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.markdown.MdLayout
import com.sample.style.WtButton
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import com.sample.style.WtRows
import io.github.amanshuraikwar.portfolio.markdown.BlogListDataItem
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr

@Composable
fun BlogView(
    blogData: List<BlogListDataItem>,
) {
    Div(
        attrs = {
            classes(
                WtRows.wtRow,
            )
        }
    ) {
        blogData.forEach { blogListDataItem ->
            A(
                attrs = {
                    target(ATarget.Blank)
                    classes(
                        WtOffsets.wtTopOffset96,
                        WtOffsets.wtTopOffsetSm48
                    )
                },
                href = blogListDataItem.link,
            ) {
                MdLayout(
                    attrs = {
                        classes(WtButton.wtButtonBlog)
                    },
                    nodes = listOf(
                        MdNode.H1(
                            blogListDataItem.title
                        ),
                        MdNode.Date(
                            blogListDataItem.date
                        ),
                        MdNode.P(
                            blogListDataItem.firstParagraph
                        ),
                    )
                )
            }

            Hr(
                attrs = {
                    classes(
                        WtContent.sectionDividerHr,
                        WtOffsets.wtTopOffset96,
                        WtOffsets.wtTopOffsetSm48,
                    )

                    style {
                        width(100.percent)
                    }
                }
            )
        }
    }
}