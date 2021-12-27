@file:Suppress("EXPERIMENTAL_API_USAGE")

package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.model.AppData
import io.github.amanshuraikwar.portfolio.model.AppLink
import io.github.amanshuraikwar.portfolio.model.ExperienceData
import io.github.amanshuraikwar.portfolio.model.LinkData
import io.github.amanshuraikwar.portfolio.model.PortfolioData
import io.github.amanshuraikwar.portfolio.network.PortfolioApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioRepository(
    private val portfolioApi: PortfolioApi = PortfolioApi(
        client = Factory.createHttpClient(enableNetworkLogs = true)
    ),
    private val dataStore: DataStore = GeneratedDataStore(),
) {
    private suspend fun getPortfolioData(): PortfolioData {
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

    suspend fun getPageData(): PageData {
        return when (dataStore.getPageType()) {
            PageType.HOME -> PageData.Home(
                getPortfolioData(),
                BlogListDataStore().getBlogListData()
            )
            PageType.MD -> PageData.Md(getPortfolioData(), dataStore.getData())
            PageType.PROJECTS -> PageData.Projects(getPortfolioData())
            PageType.BACKGROUND -> PageData.Background(getPortfolioData())
            PageType.ABOUT_ME -> PageData.AboutMe(getPortfolioData())
        }
    }
}