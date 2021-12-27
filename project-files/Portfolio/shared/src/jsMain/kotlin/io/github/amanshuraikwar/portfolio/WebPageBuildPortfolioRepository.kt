package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.model.PageData
import io.github.amanshuraikwar.portfolio.network.PortfolioApi

class WebPageBuildPortfolioRepository(
    portfolioApi: PortfolioApi = PortfolioApi(
        client = Factory.createHttpClient(enableNetworkLogs = true)
    ),
    private val dataStore: WebPageBuildDataStore = GeneratedWebPageBuildDataStore(),
) : PortfolioRepository(
    portfolioApi = portfolioApi
) {
    suspend fun getPageData(): PageData {
        return when (dataStore.getPageType()) {
            PageType.HOME -> PageData.Home(
                getPortfolioData(),
                GeneratedBlogListDataStore().getBlogListData()
            )
            PageType.MD -> PageData.Md(getPortfolioData(), dataStore.getData())
            PageType.PROJECTS -> PageData.Projects(getPortfolioData())
            PageType.BACKGROUND -> PageData.Background(getPortfolioData())
            PageType.ABOUT_ME -> PageData.AboutMe(getPortfolioData())
        }
    }
}