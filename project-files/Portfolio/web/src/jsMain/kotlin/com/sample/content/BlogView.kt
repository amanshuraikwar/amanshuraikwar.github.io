package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.markdown.MdLayout
import com.sample.style.WtButton
import com.sample.style.WtCols
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
                WtOffsets.wtTopOffset96,
                WtOffsets.wtTopOffsetSm48,
            )
        }
    ) {
        blogData.forEach { blogListDataItem ->
            A(
                attrs = {
                    classes(
                        WtButton.wtButtonBlog,
                        WtCols.wtCol12
                    )
                },
                href = blogListDataItem.link,
            ) {
                MdLayout(
                    attrs = {
                        classes(
                            WtOffsets.wtTopOffset48,
                            WtOffsets.wtTopOffsetSm24,
                        )
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

                Div(
                    attrs = {
                        classes(
                            WtOffsets.wtTopOffset48,
                            WtOffsets.wtTopOffsetSm24,
                        )
                    }
                )
            }

            Hr(
                attrs = {
                    classes(
                        WtContent.sectionDividerHr,
//                        WtOffsets.wtTopOffset96,
//                        WtOffsets.wtTopOffsetSm48,
                    )

                    style {
                        width(100.percent)
                    }
                }
            )
        }
    }
}