package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.markdown.BlogListDataItem

class BlogListDataStore {

	fun getBlogListData(): List<BlogListDataItem> {
		return listOf(
            BlogListDataItem(
                title = "My New Portfolio Website",
                date = "December 9, 2021",
                firstParagraph = "This is my new portfolio website built with KMM & Jetpack Compose.",
                link = "https://amanshuraikwar.github.io/portfolio",
            ),
            BlogListDataItem(
                title = "Introducing Next Bus SG",
                date = "December 8, 2021",
                firstParagraph = "After working on it for more than a year, I present to you Next Bus SG. Next Bus SG is a minimal & light app to see bus arrival timings in Singapore. Scroll down to the bottom for download links.",
                link = "https://amanshuraikwar.github.io/nextbus",
            ),
		)
	}
}