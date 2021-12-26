package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.markdown.MdNode

interface DataStore {
	fun getPageType(): PageType
	fun getData(): List<MdNode>
}
