package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.markdown.BlogListDataItem

class BlogListDataStore {

	fun getBlogListData(): List<BlogListDataItem> {
		return listOf(
            BlogListDataItem(
                title = "Issues using MockK in a KMM shared module",
                date = "December 12, 2021",
                firstParagraph = "If you are writing unit tests for your Kotlin project and need to mock certain objects, there is high chance that you are using MockK. Here are a couple of issues that I faced while using MockK in my KMM project. The project has Android and iOS apps with business logic written in shared KMM modules, I was writing unit tests to test this business logic.",
                link = "https://amanshuraikwar.github.io/mockk-kmm-issues",
            ),
            BlogListDataItem(
                title = "My Portfolio",
                date = "December 11, 2021",
                firstParagraph = "This is my portfolio. It is a KMM project which runs on Web, Android, iOS. The business logic is written in Kotlin. The Web app is written in Kotlin, built using Jetpack Compose for Web. The Android app is written in Kotlin, built using Jetpack Compose. The iOS app UI is written in Swift, built using Swift UI and it uses the KMM shared module for business logic.",
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