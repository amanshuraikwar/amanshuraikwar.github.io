@file:Suppress("EXPERIMENTAL_API_USAGE")

package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.blog.BlogListDataItem
import io.github.amanshuraikwar.portfolio.model.ProjectData
import io.github.amanshuraikwar.portfolio.model.AppLink
import io.github.amanshuraikwar.portfolio.model.BackgroundData
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
                    projects = response.apps.map { appDataResponse ->
                        ProjectData(
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
                    background = response.experience.map {
                        BackgroundData(
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
            portfolioApi.getBlogPageData(pageId)
                .blogData
                .toMdNodes()
        }
    }

    suspend fun getProjectsData(): List<ProjectData> {
        return withContext(Dispatchers.Default) {
            portfolioApi
                .getPortfolioData()
                .apps
                .map { appDataResponse ->
                    ProjectData(
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

                }
        }
    }

    suspend fun getBackgroundData(): List<BackgroundData> {
        return withContext(Dispatchers.Default) {
            portfolioApi
                .getPortfolioData()
                .experience
                .map {
                    BackgroundData(
                        title = it.title,
                        location = it.location,
                        dateRange = it.dateRange,
                        content = it.content
                    )
                }
        }
    }
}