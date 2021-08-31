import SwiftUI
import shared

struct ContentView: View {
	let homePageDataList = PortfolioRepository().getHomePageDataList()
    @StateObject var viewModel = ViewModel(repository: PortfolioRepository())
    
    var body: some View {
        HomePageView(screenState: $viewModel.screenState)
            .onAppear {
                viewModel.getPortfolioData()
            }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
}
