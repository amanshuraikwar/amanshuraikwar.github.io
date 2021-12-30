@file:Suppress("EXPERIMENTAL_API_USAGE")

package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.blog.BlogListDataItem
import io.github.amanshuraikwar.portfolio.model.AppData
import io.github.amanshuraikwar.portfolio.model.AppLink
import io.github.amanshuraikwar.portfolio.model.ExperienceData
import io.github.amanshuraikwar.portfolio.model.LinkData
import io.github.amanshuraikwar.portfolio.model.MdNode
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.network.PortfolioApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class PortfolioRepository(
    private val portfolioApi: PortfolioApi = PortfolioApi(
        client = Factory.createHttpClient(enableNetworkLogs = true)
    ),
) {
    protected suspend fun getPortfolioData(): PortfolioData {
        return withContext(Dispatchers.Default) {
            portfolioApi.getPortfolioData().let { response ->
                PortfolioData(
                    name = response.name,
                    intro = response.intro,
                    links = response.links.map { (id, title, url) ->
                        LinkData(id, title, url)
                    },
                    apps = response.apps.map { appDataResponse ->
                        AppData(
                            appDataResponse.id,
                            appDataResponse.title,
                            appDataResponse.description,
                            appDataResponse.maintained,
                            appDataResponse.art,
                            appDataResponse.appLinks.map {
                                when (it.type) {
                                    "github" -> AppLink.Github(it.url)
                                    "playstore" -> AppLink.PlayStore(it.url)
                                    "download" -> AppLink.Download(it.url)
                                    else -> AppLink.Other(it.url)
                                }
                            }
                        )
                    },
                    experience = response.experience.map {
                        ExperienceData(
                            title = it.title,
                            location = it.location,
                            dateRange = it.dateRange,
                            content = it.content
                        )
                    },
                    madeWith = response.madeWith
                )
            }
        }
    }

    suspend fun getBlogListData(): List<BlogListDataItem> {
        return withContext(Dispatchers.Default) {
            portfolioApi.getBlogListData().blogList.map {
                BlogListDataItem(
                    title = it.title,
                    date = it.date,
                    firstParagraph = it.firstParagraph,
                    link = it.link,
                    id = it.id
                )
            }
        }
    }

    suspend fun getBlogPageData(pageId: String): List<MdNode> {
        return withContext(Dispatchers.Default) {
            val mdNodeList = mutableListOf<MdNode>()
            portfolioApi.getBlogPageData(pageId)
                .blogData
                .forEach { map ->
                    when (map["type"]) {
                        "H1" -> {
                            mdNodeList.add(
                                MdNode.H1(
                                    text = map["text"] ?: ""
                                )
                            )
                        }
                        "H3" -> {
                            mdNodeList.add(
                                MdNode.H3(
                                    text = map["text"] ?: ""
                                )
                            )
                        }
                        "P" -> {
                            mdNodeList.add(
                                MdNode.P(
                                    text = map["text"] ?: ""
                                )
                            )
                        }
                        "IMG" -> {
                            mdNodeList.add(
                                MdNode.Img(
                                    label = map["label"] ?: "",
                                    url = map["url"]?.let {
                                        if (it.startsWith("../")) {
                                            "https://amanshuraikwar.github.io/${it.drop(3)}"
                                        } else {
                                            it
                                        }
                                    } ?: ""
                                )
                            )
                        }
                        "Spacer" -> {
                            mdNodeList.add(
                                MdNode.Spacer
                            )
                        }
                        "DATE" -> {
                            mdNodeList.add(
                                MdNode.Date(
                                    text = map["text"] ?: ""
                                )
                            )
                        }
                        "BTN" -> {
                            mdNodeList.add(
                                MdNode.Btn(
                                    text = map["text"] ?: "",
                                    url = map["url"] ?: ""
                                )
                            )
                        }
                        "CODE" -> {
                            mdNodeList.add(
                                MdNode.Code(
                                    code = map["code"] ?: "",
                                    language = map["language"] ?: ""
                                )
                            )
                        }
                    }
                }
            mdNodeList
        }
    }
}