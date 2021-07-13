import SwiftUI
import shared

struct ContentView: View {
	let homePageDataList = PortfolioRepository().getHomePageDataList()

    var body: some View {
        HomePageView(homePageDataList: homePageDataList)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
