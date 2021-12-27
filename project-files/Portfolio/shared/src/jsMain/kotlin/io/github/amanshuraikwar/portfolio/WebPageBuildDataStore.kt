package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.model.MdNode

interface WebPageBuildDataStore {
    fun getPageType(): PageType
    fun getData(): List<MdNode>
}
