package com.sample.content

import androidx.compose.runtime.Composable
import com.sample.markdown.MdLayout
import com.sample.style.WtButton
import com.sample.style.WtCols
import com.sample.style.WtContainer
import com.sample.style.WtContent
import com.sample.style.WtOffsets
import io.github.amanshuraikwar.portfolio.markdown.BlogListDataItem
import io.github.amanshuraikwar.portfolio.markdown.MdNode
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr

@Composable
fun BlogView(
    blogData: List<BlogListDataItem>,
) {
    blogData.forEachIndexed { index, blogListDataItem ->
        A(
            attrs = {
                classes(
                    WtButton.wtButtonBlog,
                    WtCols.wtCol12
                )
            },
            href = blogListDataItem.link,
        ) {
            Div(
                attrs = {
                    classes(
                        WtContainer.wtContainerSm,
                        WtOffsets.wtTopOffset48,
                        WtOffsets.wtTopOffsetSm24,
                    )
                }
            )

            MdLayout(
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
                        WtContainer.wtContainerSm,
                        WtOffsets.wtTopOffset48,
                        WtOffsets.wtTopOffsetSm24,
                    )
                }
            )
        }

        if (index != blogData.size - 1) {
            Hr(
                attrs = {
                    classes(
                        WtContainer.wtContainerSm,
                        WtContent.sectionDividerHr,
                    )
                }
            )
        }
    }
}