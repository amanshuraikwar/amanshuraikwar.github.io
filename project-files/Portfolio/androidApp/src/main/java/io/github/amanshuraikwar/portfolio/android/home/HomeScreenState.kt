package io.github.amanshuraikwar.portfolio.android.home

import io.github.amanshuraikwar.portfolio.model.PortfolioData

sealed class HomeScreenState {
    object Fetching : HomeScreenState()

    data class Success(
        val portfolioData: PortfolioData,
    ) : HomeScreenState()
}