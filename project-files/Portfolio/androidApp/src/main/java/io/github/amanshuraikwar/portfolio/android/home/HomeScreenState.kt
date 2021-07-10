package io.github.amanshuraikwar.portfolio.android.home

import io.github.amanshuraikwar.portfolio.model.HomePageData

sealed class HomeScreenState {
    object Fetching : HomeScreenState()

    data class Success(
        val homePageDataList: List<HomePageData>
    ) : HomeScreenState()
}