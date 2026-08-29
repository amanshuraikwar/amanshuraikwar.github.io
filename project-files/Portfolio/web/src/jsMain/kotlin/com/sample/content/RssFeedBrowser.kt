package com.sample.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sample.components.TextHrefButton
import com.sample.style.AppCSSVariables
import com.sample.style.WtButton
import com.sample.style.WtCols
import com.sample.style.WtRows
import com.sample.style.WtTexts
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.Document
import org.w3c.dom.Element
import kotlin.js.unsafeCast

private data class FeedDefinition(
    val id: String,
    val title: String,
    val description: String,
    val sourceUrl: String,
    val rssUrl: String,
)

private data class FeedItem(
    val title: String,
    val link: String,
    val description: String,
    val imageUrl: String?,
    val categories: List<String>,
)

private sealed class FeedLoadState {
    object Loading : FeedLoadState()
    data class Loaded(val items: List<FeedItem>) : FeedLoadState()
    data class Error(val message: String) : FeedLoadState()
}

private val feedDefinitions = listOf(
    FeedDefinition(
        id = "inspiration-grid-photography",
        title = "Inspiration Grid — Photography",
        description = "Recent photography inspiration.",
        sourceUrl = "https://theinspirationgrid.com/category/photography/",
        rssUrl = "/rss/inspiration-grid-photography.xml",
    ),
    FeedDefinition(
        id = "inspiration-grid-illustration",
        title = "Inspiration Grid — Illustration",
        description = "Recent illustration inspiration.",
        sourceUrl = "https://theinspirationgrid.com/category/illustration/",
        rssUrl = "/rss/inspiration-grid-illustration.xml",
    ),
    FeedDefinition(
        id = "inspiration-grid-industrial-design",
        title = "Inspiration Grid — Industrial Design",
        description = "Recent industrial design inspiration.",
        sourceUrl = "https://theinspirationgrid.com/category/industrial-design/",
        rssUrl = "/rss/inspiration-grid-industrial-design.xml",
    ),
    FeedDefinition(
        id = "site-of-sites-websites",
        title = "Site of Sites — Websites",
        description = "Recent web design inspiration.",
        sourceUrl = "https://www.siteofsites.co/?p=1",
        rssUrl = "/rss/site-of-sites-websites.xml",
    ),
)

@Composable
fun RssFeedBrowser() {
    var selectedFeed by remember { mutableStateOf(feedDefinitions.first()) }
    var loadState: FeedLoadState by remember { mutableStateOf(FeedLoadState.Loading) }

    LaunchedEffect(selectedFeed.id) {
        loadState = FeedLoadState.Loading
        loadState = try {
            FeedLoadState.Loaded(fetchFeedItems(selectedFeed.rssUrl))
        } catch (error: Throwable) {
            FeedLoadState.Error(error.message ?: "The RSS file could not be loaded.")
        }
    }

    Div(
        attrs = {
            classes(WtRows.wtRow)
            style { gap(24.px) }
        }
    ) {
        Div(
            attrs = {
                classes(WtCols.wtCol3, WtCols.wtColMd4, WtCols.wtColSm12)
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    gap(12.px)
                }
            }
        ) {
            feedDefinitions.forEach { feed ->
                Div(
                    attrs = {
                        classes(WtButton.wtButtonBlog)
                        style {
                            padding(16.px)
                            borderRadius(16.px)
                            border {
                                width = if (feed.id == selectedFeed.id) 2.px else 1.px
                                style = LineStyle.Solid
                                color = AppCSSVariables.colorOnBackground.value()
                            }
                        }
                        onClick { selectedFeed = feed }
                    }
                ) {
                    Div(attrs = { classes(WtTexts.wtH6) }) { Text(feed.title) }
                    Div(
                        attrs = {
                            classes(WtTexts.wtBody)
                            style { marginTop(8.px) }
                        }
                    ) { Text(feed.description) }
                }
            }
        }

        Div(
            attrs = {
                classes(WtCols.wtCol8, WtCols.wtColMd8, WtCols.wtColSm12)
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    gap(20.px)
                }
            }
        ) {
            Div(attrs = { classes(WtTexts.wtH3Blog) }) { Text(selectedFeed.title) }
            Div(attrs = { classes(WtTexts.wtBody) }) { Text("Parsed directly from the published RSS file.") }
            Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Center)
                        gap(12.px)
                    }
                }
            ) {
                TextHrefButton(text = "View source", href = selectedFeed.sourceUrl, buttonSize = com.sample.components.ButtonSize.SMALL)
                TextHrefButton(text = "RSS XML", href = selectedFeed.rssUrl, buttonStyle = com.sample.components.ButtonStyle.OUTLINE, buttonSize = com.sample.components.ButtonSize.SMALL)
            }

            when (val state = loadState) {
                FeedLoadState.Loading -> Div(attrs = { classes(WtTexts.wtBody) }) { Text("Loading feed…") }
                is FeedLoadState.Error -> Div(attrs = { classes(WtTexts.wtBody) }) { Text(state.message) }
                is FeedLoadState.Loaded -> {
                    state.items.forEach { item -> FeedItemView(item) }
                }
            }
        }
    }
}

@Composable
private fun FeedItemView(item: FeedItem) {
    A(
        href = item.link,
        attrs = {
            target(ATarget.Blank)
            classes(WtButton.wtButtonBlog)
            style {
                display(DisplayStyle.Flex)
                gap(16.px)
                padding(16.px)
                borderRadius(16.px)
            }
        }
    ) {
        item.imageUrl?.let { imageUrl ->
            Img(
                src = imageUrl,
                attrs = {
                    style {
                        width(120.px)
                        borderRadius(12.px)
                        property("object-fit", "cover")
                    }
                }
            )
        }
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    gap(8.px)
                }
            }
        ) {
            Div(attrs = { classes(WtTexts.wtH5) }) { Text(item.title) }
            Div(attrs = { classes(WtTexts.wtBody) }) { Text(item.description) }
            if (item.categories.isNotEmpty()) {
                Div(attrs = { classes(WtTexts.wtCaption) }) { Text(item.categories.joinToString(" · ")) }
            }
        }
    }
}

private suspend fun fetchFeedItems(url: String): List<FeedItem> {
    val response = window.fetch(url).await()
    check(response.ok) { "Unable to load the RSS file (${response.status})." }
    return parseRss(response.text().await())
}

private fun parseRss(xml: String): List<FeedItem> {
    val parser = js("new DOMParser()").unsafeCast<BrowserDomParser>()
    val document = parser.parseFromString(xml, "application/xml")
    check(document.getElementsByTagName("parsererror").length == 0) { "The RSS file is not valid XML." }

    val items = document.getElementsByTagName("item")
    return (0 until items.length).mapNotNull { index ->
        val item = items.item(index) as? Element ?: return@mapNotNull null
        val title = item.textForTag("title")
        val link = item.textForTag("link")
        if (title.isBlank() || link.isBlank()) return@mapNotNull null
        FeedItem(
            title = title,
            link = link,
            description = item.textForTag("description"),
            imageUrl = (item.getElementsByTagName("media:content").item(0) as? Element)?.getAttribute("url")?.ifBlank { null },
            categories = (0 until item.getElementsByTagName("category").length).mapNotNull { categoryIndex ->
                item.getElementsByTagName("category").item(categoryIndex)?.textContent?.trim()?.ifBlank { null }
            },
        )
    }
}

private fun Element.textForTag(tag: String): String =
    getElementsByTagName(tag).item(0)?.textContent?.trim().orEmpty()

private external interface BrowserDomParser {
    fun parseFromString(source: String, mimeType: String): Document
}
