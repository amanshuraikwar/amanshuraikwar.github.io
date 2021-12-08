package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.markdown.MdNode

class MdDataStore {

	fun mdEnabled(): Boolean {
		return false
	}

	fun getData(): List<MdNode> {
		return listOf()
	}
}
